package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.ConvertUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.product.controller.vo.PmsSpuImagesVo;
import com.danbro.product.entity.PmsSpuImages;
import com.danbro.product.mapper.PmsSpuImagesMapper;
import com.danbro.product.service.PmsSpuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * spu图片(PmsSpuImages)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Service
public class PmsSpuImagesServiceImpl extends ServiceImpl<PmsSpuImagesMapper, PmsSpuImages> implements PmsSpuImagesService {

    @Override
    public List<PmsSpuImagesVo> batchSave(List<String> images, Long spuId) {
        List<PmsSpuImages> spuImages = images.stream().map(image -> PmsSpuImages.builder().build().setSpuId(spuId).setImgUrl(MyStrUtils.getImageName(image))).collect(Collectors.toList());
        boolean saveBatch = this.saveBatch(spuImages);
        List<PmsSpuImagesVo> imagesVoList = ConvertUtils.batchConvert(spuImages, PmsSpuImagesVo.class);
        return MyCurdUtils.batchInsertOrUpdate(imagesVoList, saveBatch, ResponseCode.INSERT_FAILURE);
    }
}