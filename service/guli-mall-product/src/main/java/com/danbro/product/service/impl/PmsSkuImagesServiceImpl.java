package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.ConvertUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.product.controller.vo.PmsSkuImagesVo;
import com.danbro.product.entity.PmsSkuImages;
import com.danbro.product.mapper.PmsSkuImagesMapper;
import com.danbro.product.service.PmsSkuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<PmsSkuImagesVo> skuImagesVos = ConvertUtils.batchConvert(pmsSkuImages, PmsSkuImagesVo.class);
        return MyCurdUtils.batchInsertOrUpdate(skuImagesVos, saveBatch, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public List<PmsSkuImagesVo> getSkuImageBySkuId(Long skuId) {
        List<PmsSkuImages> pmsSkuImages = MyCurdUtils.selectList(this.list(new QueryWrapper<PmsSkuImages>().lambda().eq(PmsSkuImages::getSkuId, skuId)), ResponseCode.NOT_FOUND);
        return ConvertUtils.batchConvert(pmsSkuImages, PmsSkuImagesVo.class);
    }
}