package com.danbro.order.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.order.controller.vo.PmsSkuImagesVo;
import com.danbro.order.entity.PmsSkuImages;


/**
 * sku图片(PmsSkuImages)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsSkuImagesService extends IService<PmsSkuImages> {
    /**
     * 批量添加sku图片
     *
     * @param skuImageVoList 添加的sku图片
     * @return 添加完毕后的sku图片
     */
    List<PmsSkuImagesVo> batchSaveSkuImages(List<PmsSkuImagesVo> skuImageVoList);

    /**
     * 获取 sku 的图片
     *
     * @param skuId skuID
     * @return sku 的所有图片
     */
    List<PmsSkuImagesVo> getSkuImageBySkuId(Long skuId);
}