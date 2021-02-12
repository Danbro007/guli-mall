package com.danbro.product.service;
 
 
import java.util.List;
import java.util.stream.Stream;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsSkuImagesVo;
import com.danbro.product.entity.PmsSkuImages;


/**
 * sku图片(PmsSkuImages)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsSkuImagesService extends IService<PmsSkuImages> {
    /**
     * 批量添加sku图片
     * @param skuImageVoList 添加的sku图片
     * @return 添加完毕后的sku图片
     */
    List<PmsSkuImagesVo> batchSaveSkuImages(List<PmsSkuImagesVo> skuImageVoList);
}