package com.danbro.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.product.controller.vo.PmsSkuImagesVo;
import com.danbro.product.entity.PmsSkuImages;
import com.danbro.product.mapper.PmsSkuImagesMapper;
import com.danbro.product.service.PmsSkuImagesService;
import org.springframework.stereotype.Service;

/**
 * sku图片(PmsSkuImages)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsSkuImagesServiceImpl extends ServiceImpl<PmsSkuImagesMapper, PmsSkuImages> implements PmsSkuImagesService {

    @Override
    public List<PmsSkuImagesVo> batchSaveSkuImages(List<PmsSkuImagesVo> skuImageVoList) {
        List<PmsSkuImages> pmsSkuImages = skuImageVoList.stream().map(PmsSkuImagesVo::convertToEntity).collect(Collectors.toList());
        boolean saveBatch = this.saveBatch(pmsSkuImages);
        List<PmsSkuImagesVo> skuImagesVos = pmsSkuImages.stream().map(skuImage -> PmsSkuImagesVo.builder().build().convertToVo(skuImage)).collect(Collectors.toList());
        return MyCurdUtils.batchInsertOrUpdate(skuImagesVos,saveBatch, ResponseCode.INSERT_FAILURE);
    }
}