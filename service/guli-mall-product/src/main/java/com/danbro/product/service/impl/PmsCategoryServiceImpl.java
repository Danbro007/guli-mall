package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.enums.pms.CatSortType;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.entity.PmsCategory;
import com.danbro.product.mapper.PmsCategoryMapper;
import com.danbro.product.service.PmsCategoryBrandRelationService;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品三级分类(PmsCategory)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {

    @Autowired
    private PmsCategoryBrandRelationService pmsCategoryBrandRelationService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String CATEGORY_TREE = "category-tree";

    @Override
    public List<PmsCategoryVo> getCategoryTree() {
        // 尝试到缓存去取
        List<PmsCategoryVo> pmsCategoryListFromCache = (List<PmsCategoryVo>) redisTemplate.opsForValue().get(CATEGORY_TREE);
        if (!MyCollectionUtils.isEmpty(pmsCategoryListFromCache)) {
            return pmsCategoryListFromCache;
        }
        // 缓存没有到数据库去取然后放入 redis 中
        // 先找所有的一级分类
        List<PmsCategory> allPmsCategory = this.list();
        List<PmsCategoryVo> pmsCategoryVos = allPmsCategory.stream().filter(e -> 0 == e.getParentCid()).map(m -> {
            PmsCategoryVo categoryVo = PmsCategoryVo.builder().build().convert(m);
            categoryVo.setChildren(getChildren(categoryVo, allPmsCategory));
            return categoryVo;
        }).sorted(Comparator.comparingInt(PmsCategoryVo::getSort)).collect(Collectors.toList());
        redisTemplate.opsForValue().set(CATEGORY_TREE, pmsCategoryVos);
        return pmsCategoryVos;
    }

    @Override
    public void batchDeleteCategoryById(String[] catIds) {
        // 先删除缓存
        redisTemplate.delete(CATEGORY_TREE);
        List<String> catIdList = Arrays.asList(catIds);
        QueryWrapper<PmsCategory> queryWrapper = new QueryWrapper<>();
        // 删除 categoryId或者父级Id为要删除的分类Id的对象
        queryWrapper.in("cat_id", catIdList).or().in("parent_cid", catIdList);
        // 删除完毕就不建议立即主动建立缓存，因为可能建立的缓存次数比删除的次数少，降低效率。
        MyCurdUtils.delete(this.remove(queryWrapper), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public PmsCategory insert(PmsCategory category) {
        redisTemplate.delete(CATEGORY_TREE);
        return MyCurdUtils.insertOrUpdate(category, this.saveOrUpdate(category), ResponseCode.INSERT_FAILURE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCategory update(PmsCategory category) {
        redisTemplate.delete(CATEGORY_TREE);
        PmsCategory pmsCategory = MyCurdUtils.insertOrUpdate(category, this.saveOrUpdate(category), ResponseCode.UPDATE_FAILURE);
        // 如果是三级分类则同步更新 CategoryBrandRelation
        if (pmsCategory.getCatLevel().equals(CatSortType.THREE)) {
            pmsCategoryBrandRelationService.updateCategory(pmsCategory.getCatId(), pmsCategory.getName());
        }
        return pmsCategory;
    }

    @Override
    public PmsCategory getCategoryInfo(Long categoryId) {
        return MyCurdUtils.selectOne(this.getById(categoryId), ResponseCode.NOT_FOUND);
    }

    @Override
    public void batchUpdateCategory(List<PmsCategory> updateCategoryList) {
        redisTemplate.delete(CATEGORY_TREE);
        MyCurdUtils.batchInserOrUpdate(updateCategoryList, this.updateBatchById(updateCategoryList), ResponseCode.UPDATE_FAILURE);
    }

    @Override
    public List<Long> findCateLogPath(Long cateLogId) {
        List<Long> cateLogPath = new ArrayList<>();
        PmsCategory pmsCategory = MyCurdUtils.selectOne(this.getById(cateLogId), ResponseCode.NOT_FOUND);
        // 判断有没有父类有的话继续查找，直到没有。
        if (MyObjectUtils.isNotEmpty(pmsCategory) && pmsCategory.getParentCid() != 0) {
            cateLogPath.add(cateLogId);
            findParentCateLogId(pmsCategory.getParentCid(), cateLogPath);
        }
        Collections.reverse(cateLogPath);
        return cateLogPath;
    }

    private void findParentCateLogId(Long cateLogId, List<Long> cateLogPath) {
        cateLogPath.add(cateLogId);
        PmsCategory pmsCategory = this.getById(cateLogId);
        if (MyObjectUtils.isNotEmpty(pmsCategory.getParentCid()) && pmsCategory.getParentCid() != 0) {
            findParentCateLogId(pmsCategory.getParentCid(), cateLogPath);
        }
    }


    /**
     * 获取父分类的子分类
     *
     * @param root        父分类
     * @param allCategory 所有分类列表
     * @return 子分类
     */
    private List<PmsCategoryVo> getChildren(PmsCategoryVo root, List<PmsCategory> allCategory) {
        return allCategory.stream().filter(e -> e.getParentCid().equals(root.getCatId())).map(m -> {
            PmsCategoryVo categoryVo = PmsCategoryVo.builder().build().convert(m);
            categoryVo.setChildren(getChildren(categoryVo, allCategory));
            return categoryVo;
        }).sorted(Comparator.comparingInt(PmsCategoryVo::getSort)).collect(Collectors.toList());
    }
}