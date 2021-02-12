package com.danbro.product.service;
 
 
import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsSkuInfoVo;
import com.danbro.product.controller.vo.spu.Skus;
import com.danbro.product.entity.PmsSkuInfo;


/**
 * sku信息(PmsSkuInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsSkuInfoService extends IService<PmsSkuInfo> {
    /**
     * 批量添加Sku
     * @param skus 批量的skuInfoVo
     */
    void batchSaveSkuInfo(List<PmsSkuInfoVo> skus);
}