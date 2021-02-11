package com.danbro.product.service.impl;

import java.util.Arrays;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.product.controller.vo.PmsBrandVo;
import com.danbro.product.entity.PmsBrand;
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
    public Pagination<PmsBrandVo, PmsBrand> queryPage(PageParam<PmsBrand> pageParam, String key) {
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", key).or().like("name", key);
        IPage<PmsBrand> page = this.page(new Query<PmsBrand>().getPage(pageParam), queryWrapper);
        return new Pagination<>(page, PmsBrandVo.class);
    }

    @Override
    public PmsBrandVo insert(PmsBrandVo brand) {
        return MyCurdUtils.insertOrUpdate(brand, this.saveOrUpdate(brand.convertToEntity()), ResponseCode.INSERT_FAILURE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsBrandVo update(PmsBrandVo brand) {
        PmsBrand pmsBrand = MyCurdUtils.insertOrUpdate(brand.convertToEntity(), this.saveOrUpdate(brand.convertToEntity()), ResponseCode.UPDATE_FAILURE);
        pmsCategoryBrandRelationService.updateBrand(pmsBrand.getBrandId(), pmsBrand.getName(),false);
        return brand;
    }

    @Override
    public PmsBrandVo getBrandInfoById(Long brandId) {
        PmsBrand pmsBrand = MyCurdUtils.select(this.getById(brandId), ResponseCode.NOT_FOUND);
        PmsBrandVo pmsBrandVo = new PmsBrandVo();
        return pmsBrandVo.convertToVo(pmsBrand);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteBrand(Long[] ids) {
        MyCurdUtils.batchDelete(this.removeByIds(Arrays.asList(ids)), ResponseCode.DELETE_FAILURE);
        // 同步删除 CategoryBrandRelation 里的数据
        pmsCategoryBrandRelationService.batchDeleteByBrandId(ids);
    }
}