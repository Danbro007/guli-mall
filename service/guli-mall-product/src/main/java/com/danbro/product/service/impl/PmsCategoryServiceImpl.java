package com.danbro.product.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.enums.pms.CatSortType;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.product.controller.vo.PmsCategory2Vo;
import com.danbro.product.controller.vo.PmsCategory3Vo;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.entity.PmsCategory;
import com.danbro.product.mapper.PmsCategoryMapper;
import com.danbro.product.service.PmsAttrService;
import com.danbro.product.service.PmsCategoryBrandRelationService;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PmsAttrService pmsAttrService;

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
            PmsCategoryVo categoryVo = PmsCategoryVo.builder().build().convertToVo(m);
            categoryVo.setChildren(getChildren(categoryVo, allPmsCategory));
            return categoryVo;
        }).sorted(Comparator.comparingInt(PmsCategoryVo::getSort)).collect(Collectors.toList());
        // 找到的数据放入缓存中
        redisTemplate.opsForValue().set(CATEGORY_TREE, pmsCategoryVos);
        return pmsCategoryVos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteCategoryById(Long[] catIds) {
        // 先删除缓存
        redisTemplate.delete(CATEGORY_TREE);
        List<Long> catIdList = Arrays.asList(catIds);
        QueryWrapper<PmsCategory> queryWrapper = new QueryWrapper<>();
        // 删除 categoryId或者父级Id为要删除的分类Id的对象
        queryWrapper.in("cat_id", catIdList).or().in("parent_cid", catIdList);
        // 删除完毕就不建议立即主动建立缓存，因为可能建立的缓存次数比删除的次数少，降低效率。
        MyCurdUtils.delete(this.remove(queryWrapper), ResponseCode.DELETE_FAILURE);
        // 同步删除 pms_category_brand_relation 里的数据
        pmsCategoryBrandRelationService.batchDeleteByCategoryId(catIds);
        // 同步删除 pms_attr 表的数据
        pmsAttrService.batchDeleteAttr(catIds);
    }

    @Override
    public PmsCategoryVo insert(PmsCategoryVo category) {
        redisTemplate.delete(CATEGORY_TREE);
        return MyCurdUtils.insertOrUpdate(category, this.saveOrUpdate(category.convertToEntity()), ResponseCode.INSERT_FAILURE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsCategoryVo update(PmsCategoryVo category) {
        // 请清空缓存
        redisTemplate.delete(CATEGORY_TREE);
        PmsCategoryVo pmsCategoryVo = MyCurdUtils.insertOrUpdate(category, this.saveOrUpdate(category.convertToEntity()), ResponseCode.UPDATE_FAILURE);
        // 如果是三级分类则同步更新 CategoryBrandRelation
        if (pmsCategoryVo.getCatLevel().equals(CatSortType.THREE)) {
            pmsCategoryBrandRelationService.updateCategory(pmsCategoryVo.getCatId(), pmsCategoryVo.getName());
        }
        return pmsCategoryVo;
    }

    @Override
    public PmsCategoryVo getCategoryInfo(Long categoryId, Boolean throwException) {
        PmsCategory pmsCategory = MyCurdUtils.select(this.getById(categoryId), ResponseCode.NOT_FOUND, false);
        if (MyObjectUtils.isNotNull(pmsCategory)) {
            return PmsCategoryVo.builder().build().convertToVo(pmsCategory);
        }
        return null;
    }

    @Override
    public void batchUpdateCategory(List<PmsCategory> updateCategoryList) {
        redisTemplate.delete(CATEGORY_TREE);
        MyCurdUtils.batchInsertOrUpdate(updateCategoryList, this.updateBatchById(updateCategoryList), ResponseCode.UPDATE_FAILURE);
    }

    @Override
    public String[] findCateLogPath(Long cateLogId) {
        List<String> cateLogPath = new ArrayList<>();
        PmsCategory pmsCategory = MyCurdUtils.select(this.getById(cateLogId), ResponseCode.NOT_FOUND);
        // 判断有没有父类有的话继续查找，直到没有。
        if (MyObjectUtils.isNotNull(pmsCategory) && pmsCategory.getParentCid() != 0) {
            cateLogPath.add(Long.toString(cateLogId));
            findParentCateLogId(pmsCategory.getParentCid(), cateLogPath);
        }
        Collections.reverse(cateLogPath);
        String[] array = new String[cateLogPath.size()];
        cateLogPath.toArray(array);
        return array;
    }

    @Override
    public List<PmsCategoryVo> getCategoryByCatLevel(Integer catLevel) {
        List<PmsCategory> categoryList = this.list(new QueryWrapper<PmsCategory>().lambda().eq(PmsCategory::getCatLevel, catLevel));
        return categoryList.stream().map(cat -> PmsCategoryVo.builder().build().convertToVo(cat)).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<PmsCategory2Vo>> getCategoryTreeFroFront() {
        // 1、先获取以及分类；
        List<PmsCategoryVo> firstCatList = MyCurdUtils.selectList(this.getCategoryByCatLevel(1), ResponseCode.NOT_FOUND);
        HashMap<String, List<PmsCategory2Vo>> map = new HashMap<>();
        firstCatList.forEach(first -> {
            // 2、找到下属的二级分类
            List<PmsCategory> secondCatList = this.list(new QueryWrapper<PmsCategory>().lambda().eq(PmsCategory::getParentCid, first.getCatId()));
            if (MyCollectionUtils.isNotEmpty(secondCatList)) {
                List<PmsCategory2Vo> secondVoList = secondCatList.stream().map(second -> {
                    PmsCategory2Vo category2Vo = PmsCategory2Vo.builder().build().setCatalog1Id(first.getCatId().toString()).setId(second.getCatId().toString()).setName(second.getName());
                    // 3、找到下属的三级分类
                    List<PmsCategory> thirdCatList = this.list(new QueryWrapper<PmsCategory>().lambda().eq(PmsCategory::getParentCid, second.getCatId()));
                    if (MyCollectionUtils.isNotEmpty(thirdCatList)) {
                        List<PmsCategory3Vo> category3VoList = thirdCatList.stream().map(third -> PmsCategory3Vo.builder().build().
                                setCatalog2Id(second.getCatId().toString()).
                                setId(third.getCatId().toString()).
                                setName(third.getName())).
                                collect(Collectors.toList());
                        category2Vo.setCatalog3List(category3VoList);
                    }
                    return category2Vo;
                }).collect(Collectors.toList());
                map.put(first.getCatId().toString(), secondVoList);
            }
        });
        return map;
    }

    private void findParentCateLogId(Long cateLogId, List<String> cateLogPath) {
        cateLogPath.add(Long.toString(cateLogId));
        PmsCategory pmsCategory = this.getById(cateLogId);
        if (MyObjectUtils.isNotNull(pmsCategory.getParentCid()) && pmsCategory.getParentCid() != 0) {
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
            PmsCategoryVo categoryVo = PmsCategoryVo.builder().build().convertToVo(m);
            categoryVo.setChildren(getChildren(categoryVo, allCategory));
            return categoryVo;
        }).sorted(Comparator.comparingInt(PmsCategoryVo::getSort)).collect(Collectors.toList());
    }
}