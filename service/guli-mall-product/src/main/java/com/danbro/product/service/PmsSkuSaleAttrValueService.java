package com.danbro.product.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsSkuSaleAttrValueVo;
import com.danbro.product.entity.PmsSkuSaleAttrValue;


/**
 * sku销售属性&值(PmsSkuSaleAttrValue)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
public interface PmsSkuSaleAttrValueService extends IService<PmsSkuSaleAttrValue> {
    /**
     * 批量添加Sku的销售属性
     *
     * @param attrValueVoList 销售属性
     * @return 添加结果
     */
    List<PmsSkuSaleAttrValueVo> batchSaveSaleAttrValue(List<PmsSkuSaleAttrValueVo> attrValueVoList);

    /**
     * 通过 SkuID 查找出 sku 的销售属性
     * @param skuId skuId
     * @return sku 的销售属性
     */
    List<PmsSkuSaleAttrValueVo> getSaleAttrValueListBySkuId(Long skuId);
}