package com.danbro.order.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.order.controller.vo.PmsBrandVo;
import com.danbro.order.controller.vo.PmsCategoryBrandRelationVo;
import com.danbro.order.entity.PmsCategoryBrandRelation;


/**
 * 品牌分类关联(PmsCategoryBrandRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsCategoryBrandRelationService extends IService<PmsCategoryBrandRelation> {
    /**
     * 添加分类和品牌的关系
     *
     * @param param 分类和品牌的关系数据
     * @return 添加完毕后的分类和品牌关系数据
     */
    PmsCategoryBrandRelationVo insert(PmsCategoryBrandRelationVo param);

    /**
     * 获取品牌的所有所属分类
     *
     * @param brandId 品牌ID
     * @return 品牌与分类的关系列表
     */
    List<PmsCategoryBrandRelationVo> getBrandRelationList(Long brandId);

    /**
     * 批量删除品牌和分类的关系
     *
     * @param ids 品牌分类关系ID列表
     */
    void batchDeleteCategoryBrandRelation(Long[] ids);

    /**
     * 更新品牌与分类关系里的品牌名
     *
     * @param brandId   品牌ID
     * @param brandName 要更新的品牌名
     * @param needThrowException 更新失败是否需要抛出异常
     */
    PmsCategoryBrandRelation updateBrand(Long brandId, String brandName,Boolean needThrowException);

    /**
     * 更新品牌与分类关系里的分类名
     *
     * @param categoryId   分类ID
     * @param categoryName 分类名
     */
    PmsCategoryBrandRelation updateCategory(Long categoryId, String categoryName);

    /**
     * 通过分类ID批量删除
     * @param ids 分类ID数组
     */
    void batchDeleteByCategoryId(Long[] ids);

    /**
     * 通过品牌ID批量删除
     * @param ids 品牌ID数组
     */
    void batchDeleteByBrandId(Long[] ids);

    /**
     * 查询该分类下的所有品牌
     * @param catId 分类Id
     * @return 品牌列表
     */
    List<PmsBrandVo> getBrandListByCatId(Long catId);
}