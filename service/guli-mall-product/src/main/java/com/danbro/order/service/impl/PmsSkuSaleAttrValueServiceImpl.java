package com.danbro.order.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.ConvertUtils;
import com.danbro.order.controller.vo.PmsSkuSaleAttrValueVo;
import com.danbro.order.entity.PmsSkuSaleAttrValue;
import com.danbro.order.mapper.PmsSkuSaleAttrValueMapper;
import com.danbro.order.service.PmsSkuSaleAttrValueService;
import org.springframework.stereotype.Service;

/**
 * sku销售属性&值(PmsSkuSaleAttrValue)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Service
public class PmsSkuSaleAttrValueServiceImpl extends ServiceImpl<PmsSkuSaleAttrValueMapper, PmsSkuSaleAttrValue> implements PmsSkuSaleAttrValueService {

    @Override
    public List<PmsSkuSaleAttrValueVo> batchSaveSaleAttrValue(List<PmsSkuSaleAttrValueVo> attrValueVoList) {
        List<PmsSkuSaleAttrValue> attrValueList = attrValueVoList.stream().map(PmsSkuSaleAttrValueVo::convertToEntity).collect(Collectors.toList());
        boolean saveBatch = this.saveBatch(attrValueList);
        List<PmsSkuSaleAttrValueVo> saleAttrValueVos = ConvertUtils.batchConvert(attrValueList, PmsSkuSaleAttrValueVo.class);
        return MyCurdUtils.batchInsertOrUpdate(saleAttrValueVos, saveBatch, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public List<PmsSkuSaleAttrValueVo> getSaleAttrValueListBySkuId(Long skuId) {
        List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValues = this.list(new QueryWrapper<PmsSkuSaleAttrValue>().lambda().eq(PmsSkuSaleAttrValue::getSkuId, skuId));
        return ConvertUtils.batchConvert(pmsSkuSaleAttrValues, PmsSkuSaleAttrValueVo.class);
    }
}