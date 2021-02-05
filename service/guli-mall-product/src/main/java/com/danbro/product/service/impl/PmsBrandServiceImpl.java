package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.PageUtils;
import com.danbro.product.entity.PmsBrand;
import com.danbro.product.mapper.PmsBrandMapper;
import com.danbro.product.service.PmsBrandService;
import org.springframework.stereotype.Service;

/**
 * 品牌(PmsBrand)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {


    @Override
    public PageUtils queryPage(Long page, Long limit, String key) {
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", key).or().like("name", key);
        return new PageUtils(this.page(new Page<>(page, limit), queryWrapper));
    }

    @Override
    public PmsBrand insertOrUpdate(PmsBrand brand) {
        return MyCurdUtils.insertOrUpdate(brand, this.saveOrUpdate(brand), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public PmsBrand getBrandInfoById(Long brandId) {
        return MyCurdUtils.selectOne(this.getById(brandId), ResponseCode.NOT_FOUND);
    }
}