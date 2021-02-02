package com.danbro.product.service.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.entity.PmsCategory;
import com.danbro.product.mapper.PmsCategoryMapper;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 商品三级分类(PmsCategory)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String CATEGORY_TREE = "category-tree";

    @Override
    public List<PmsCategoryVo> getCategoryTree() {
        // 尝试到缓存去取
        List<PmsCategoryVo> pmsCategoryVoListFromRedis = (List<PmsCategoryVo>) redisTemplate.opsForValue().get(CATEGORY_TREE);
        if (!MyCollectionUtils.isEmpty(pmsCategoryVoListFromRedis)) {
            return pmsCategoryVoListFromRedis;
        }
        // 缓存没有到数据库去取然后放入 redis 中
        // 先找所有的一级分类
        List<PmsCategory> allPmsCategory = this.list();
        List<PmsCategoryVo> pmsCategoryVos = allPmsCategory.stream().filter(e -> 0 == e.getParentCid()).map(m -> {
            PmsCategoryVo categoryVo = new PmsCategoryVo().convert(m);
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
        this.remove(queryWrapper);
        // 删除完毕就不建议立即主动建立缓存，可能建立的缓存次数比删除的次数少，降低效率。
    }

    @Override
    public void insertOrUpdateCategory(PmsCategory category) {
        redisTemplate.delete(CATEGORY_TREE);
        this.saveOrUpdate(category);
    }

    @Override
    public PmsCategory getCategoryInfo(Long categoryId) {
        return this.getById(categoryId);
    }

    @Override
    public void batchUpdateCategory(List<PmsCategory> updateCategoryList) {
        redisTemplate.delete(CATEGORY_TREE);
        this.updateBatchById(updateCategoryList);
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
            PmsCategoryVo categoryVo = new PmsCategoryVo().convert(m);
            categoryVo.setChildren(getChildren(categoryVo, allCategory));
            return categoryVo;
        }).sorted(Comparator.comparingInt(PmsCategoryVo::getSort)).collect(Collectors.toList());

    }
}