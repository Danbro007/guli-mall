package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.PageUtils;
import com.danbro.product.entity.PmsBrand;
import com.danbro.product.entity.PmsCategoryBrandRelation;
import com.danbro.product.mapper.PmsBrandMapper;
import com.danbro.product.service.PmsBrandService;
import com.danbro.product.service.PmsCategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 品牌(PmsBrand)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {
    @Autowired
    private PmsCategoryBrandRelationService pmsCategoryBrandRelationService;

    @Override
    public PageUtils<PmsBrand> queryPage(Long page, Long limit, String key) {
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", key).or().like("name", key);
        return new PageUtils<>(this.page(new Page<>(page, limit), queryWrapper));
    }

    @Override
    public PmsBrand insert(PmsBrand brand) {
        return MyCurdUtils.insertOrUpdate(brand, this.saveOrUpdate(brand), ResponseCode.INSERT_FAILURE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsBrand update(PmsBrand brand) {
        PmsBrand pmsBrand = MyCurdUtils.insertOrUpdate(brand, this.saveOrUpdate(brand), ResponseCode.UPDATE_FAILURE);
        pmsCategoryBrandRelationService.updateBrand(pmsBrand.getBrandId(),pmsBrand.getName());
        return pmsBrand;
    }


    @Override
    public PmsBrand getBrandInfoById(Long brandId) {
        return MyCurdUtils.selectOne(this.getById(brandId), ResponseCode.NOT_FOUND);
    }
}