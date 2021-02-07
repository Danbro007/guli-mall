package com.danbro.product.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.PageUtils;
import com.danbro.common.utils.Query;
import com.danbro.product.controller.vo.PmsAttrGroupVo;
import com.danbro.product.controller.vo.PmsBrandVo;
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
    public PageUtils<PmsBrand> queryPage(PageParam<PmsBrand> pageParam, String key) {
        QueryWrapper<PmsBrand> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", key).or().like("name", key);
        IPage<PmsBrand> page = this.page(new Query<PmsBrand>().getPage(pageParam), queryWrapper);
        return new PageUtils<>(page);
    }

    @Override
    public PmsBrand insert(PmsBrand brand) {
        return MyCurdUtils.insertOrUpdate(brand, this.saveOrUpdate(brand), ResponseCode.INSERT_FAILURE);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsBrand update(PmsBrand brand) {
        PmsBrand pmsBrand = MyCurdUtils.insertOrUpdate(brand, this.saveOrUpdate(brand), ResponseCode.UPDATE_FAILURE);
        pmsCategoryBrandRelationService.updateBrand(pmsBrand.getBrandId(), pmsBrand.getName());
        return pmsBrand;
    }

    @Override
    public PmsBrand getBrandInfoById(Long brandId) {
        return MyCurdUtils.selectOne(this.getById(brandId), ResponseCode.NOT_FOUND);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteBrand(Long[] ids) {
        MyCurdUtils.batchDelete(this.removeByIds(Arrays.asList(ids)), ResponseCode.DELETE_FAILURE);
        // 同步删除 CategoryBrandRelation 里的数据
        pmsCategoryBrandRelationService.batchDeleteByBrandId(ids);
    }
}