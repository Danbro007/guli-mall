package com.danbro.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.product.controller.vo.PmsSkuSaleAttrValueVo;
import com.danbro.product.entity.PmsSkuSaleAttrValue;
import com.danbro.product.mapper.PmsSkuSaleAttrValueMapper;
import com.danbro.product.service.PmsSkuSaleAttrValueService;
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
        List<PmsSkuSaleAttrValueVo> saleAttrValueVos = attrValueList.stream().map(saleAttr -> PmsSkuSaleAttrValueVo.builder().build().convertToVo(saleAttr)).collect(Collectors.toList());
        return MyCurdUtils.batchInsertOrUpdate(saleAttrValueVos, saveBatch, ResponseCode.INSERT_FAILURE);
    }
}