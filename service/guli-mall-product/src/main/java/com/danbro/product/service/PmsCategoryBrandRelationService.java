package com.danbro.product.service;
 
 
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.param.CategoryBrandRelationParam;
import com.danbro.product.controller.vo.PmsCategoryBrandRelationVo;
import com.danbro.product.entity.PmsCategoryBrandRelation;


/**
 * 品牌分类关联(PmsCategoryBrandRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsCategoryBrandRelationService extends IService<PmsCategoryBrandRelation> {
    /**
     * 添加或更新分类和品牌的关系
     * @param param 分类和品牌的关系数据
     * @return 添加或者更新完毕后的分类和品牌关系数据
     */
    PmsCategoryBrandRelation insertOrUpdateCategoryBrandRelation(PmsCategoryBrandRelation param);

    /**
     * 获取品牌的所有所属分类
     * @param brandId 品牌ID
     * @return 品牌与分类的关系列表
     */
    List<PmsCategoryBrandRelationVo> getBrandRelationList(Long brandId);

    /**
     * 批量删除品牌和分类的关系
     * @param ids 品牌分类关系ID列表
     */
    void batchRemove(Long[] ids);
}