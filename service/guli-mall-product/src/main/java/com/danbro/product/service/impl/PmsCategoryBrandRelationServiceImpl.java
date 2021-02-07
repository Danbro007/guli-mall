package com.danbro.product.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.product.controller.vo.PmsCategoryBrandRelationVo;
import com.danbro.product.entity.PmsBrand;
import com.danbro.product.entity.PmsCategory;
import com.danbro.product.entity.PmsCategoryBrandRelation;
import com.danbro.product.mapper.PmsCategoryBrandRelationMapper;
import com.danbro.product.service.PmsBrandService;
import com.danbro.product.service.PmsCategoryBrandRelationService;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 品牌分类关联(PmsCategoryBrandRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsCategoryBrandRelationServiceImpl extends ServiceImpl<PmsCategoryBrandRelationMapper, PmsCategoryBrandRelation> implements PmsCategoryBrandRelationService {
    @Autowired
    private PmsBrandService pmsBrandService;
    @Autowired
    private PmsCategoryService pmsCategoryService;

    @Override
    public PmsCategoryBrandRelation insertOrUpdateCategoryBrandRelation(PmsCategoryBrandRelation param) {
        PmsBrand brand = pmsBrandService.getBrandInfoById(param.getBrandId());
        PmsCategory category = pmsCategoryService.getCategoryInfo(param.getCatelogId());
        param.setBrandName(brand.getName()).setCatelogName(category.getName());
        return MyCurdUtils.insertOrUpdate(param, this.saveOrUpdate(param), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public List<PmsCategoryBrandRelationVo> getBrandRelationList(Long brandId) {
        QueryWrapper<PmsCategoryBrandRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", brandId);
        List<PmsCategoryBrandRelation> brandRelations = this.list(queryWrapper);
        ArrayList<PmsCategoryBrandRelationVo> relationVos = new ArrayList<>();
        brandRelations.forEach(e -> {
            PmsCategoryBrandRelationVo vo = PmsCategoryBrandRelationVo.builder().build().convert(e);
            relationVos.add(vo);
        });
        return relationVos;
    }

    @Override
    public void batchRemove(Long[] ids) {
        MyCurdUtils.batchInserOrUpdate(Arrays.asList(ids), this.removeByIds(Arrays.asList(ids)), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public PmsCategoryBrandRelation updateBrand(Long brandId, String brandName) {
        UpdateWrapper<PmsCategoryBrandRelation> updateWrapper = new UpdateWrapper<>();
        PmsCategoryBrandRelation brandRelation = new PmsCategoryBrandRelation().setBrandId(brandId).setBrandName(brandName);
        updateWrapper.eq("brand_id",brandId);
        return MyCurdUtils.insertOrUpdate(brandRelation,this.update(brandRelation,updateWrapper),ResponseCode.UPDATE_FAILURE);
    }

    @Override
    public PmsCategoryBrandRelation updateCategory(Long categoryId, String categoryName) {
        UpdateWrapper<PmsCategoryBrandRelation> updateWrapper = new UpdateWrapper<>();
        PmsCategoryBrandRelation brandRelation = new PmsCategoryBrandRelation().setCatelogName(categoryName);
        updateWrapper.eq("catelog_id",categoryId);
        return MyCurdUtils.insertOrUpdate(brandRelation,this.update(brandRelation,updateWrapper),ResponseCode.UPDATE_FAILURE);
    }
}