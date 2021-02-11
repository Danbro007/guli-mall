package com.danbro.product.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.product.controller.vo.PmsBrandVo;
import com.danbro.product.controller.vo.PmsCategoryBrandRelationVo;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.entity.PmsBrand;
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
    public PmsCategoryBrandRelationVo insert(PmsCategoryBrandRelationVo param) {
        PmsBrandVo brand = pmsBrandService.getBrandInfoById(param.getBrandId());
        PmsCategoryVo category = pmsCategoryService.getCategoryInfo(param.getCatelogId(), true);
        param.setBrandName(brand.getName()).setCatelogName(category.getName());
        return MyCurdUtils.insertOrUpdate(param, this.saveOrUpdate(param.convertToEntity()), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public List<PmsCategoryBrandRelationVo> getBrandRelationList(Long brandId) {
        QueryWrapper<PmsCategoryBrandRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", brandId);
        List<PmsCategoryBrandRelation> brandRelations = this.list(queryWrapper);
        ArrayList<PmsCategoryBrandRelationVo> relationVos = new ArrayList<>();
        brandRelations.forEach(e -> {
            PmsCategoryBrandRelationVo vo = PmsCategoryBrandRelationVo.builder().build().convertToVo(e);
            relationVos.add(vo);
        });
        return relationVos;
    }

    @Override
    public void batchDeleteCategoryBrandRelation(Long[] ids) {
        MyCurdUtils.batchDelete(this.removeByIds(Arrays.asList(ids)), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public PmsCategoryBrandRelation updateBrand(Long brandId, String brandName,Boolean needThrowException) {
        UpdateWrapper<PmsCategoryBrandRelation> updateWrapper = new UpdateWrapper<>();
        PmsCategoryBrandRelation brandRelation = new PmsCategoryBrandRelation().setBrandId(brandId).setBrandName(brandName);
        updateWrapper.eq("brand_id", brandId);
        return MyCurdUtils.insertOrUpdate(brandRelation, this.update(brandRelation, updateWrapper), ResponseCode.UPDATE_FAILURE,needThrowException);
    }

    @Override
    public PmsCategoryBrandRelation updateCategory(Long categoryId, String categoryName) {
        UpdateWrapper<PmsCategoryBrandRelation> updateWrapper = new UpdateWrapper<>();
        PmsCategoryBrandRelation brandRelation = new PmsCategoryBrandRelation().setCatelogName(categoryName);
        updateWrapper.eq("catelog_id", categoryId);
        return MyCurdUtils.insertOrUpdate(brandRelation, this.update(brandRelation, updateWrapper), ResponseCode.UPDATE_FAILURE);
    }

    @Override
    public void batchDeleteByCategoryId(Long[] ids) {
        LambdaQueryWrapper<PmsCategoryBrandRelation> queryWrapper = new QueryWrapper<PmsCategoryBrandRelation>().lambda().in(PmsCategoryBrandRelation::getCatelogId, Arrays.asList(ids));
        MyCurdUtils.delete(this.remove(queryWrapper), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public void batchDeleteByBrandId(Long[] ids) {
        LambdaQueryWrapper<PmsCategoryBrandRelation> queryWrapper = new QueryWrapper<PmsCategoryBrandRelation>().lambda().in(PmsCategoryBrandRelation::getBrandId, Arrays.asList(ids));
        MyCurdUtils.delete(this.remove(queryWrapper), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public List<PmsBrandVo> getBrandListByCatId(Long catId) {
        // 先查询在该分类下的品牌ID
        LambdaQueryWrapper<PmsCategoryBrandRelation> queryWrapper = new QueryWrapper<PmsCategoryBrandRelation>().lambda().eq(PmsCategoryBrandRelation::getCatelogId, catId);
        List<PmsCategoryBrandRelation> brandRelations = MyCurdUtils.select(this.list(queryWrapper), ResponseCode.NOT_FOUND);
        // 品牌对象
        List<PmsBrand> pmsBrandList = pmsBrandService.list(new QueryWrapper<PmsBrand>().lambda()
                .in(PmsBrand::getBrandId,
                        brandRelations.stream().map(PmsCategoryBrandRelation::getBrandId).
                                collect(Collectors.toList())));
        List<PmsBrandVo> pmsBrandVoList = new ArrayList<>();
        if (!MyCollectionUtils.isEmpty(pmsBrandList)) {
            pmsBrandVoList = pmsBrandList.stream().map(brand -> PmsBrandVo.builder().build().convertToVo(brand)).collect(Collectors.toList());
        }
        return MyCurdUtils.select(pmsBrandVoList, ResponseCode.NOT_FOUND, false);
    }
}