package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsSpuImagesVo;
import com.danbro.product.entity.PmsSpuImages;

import java.util.List;


/**
 * spu图片(PmsSpuImages)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
public interface PmsSpuImagesService extends IService<PmsSpuImages> {
    /**
     * 批量添加Spu图片
     *
     * @param images Spu图片地主列表
     * @param spuId  SpuId
     * @return 添加完毕后的结果
     */
    List<PmsSpuImagesVo> batchSave(List<String> images, Long spuId);
}