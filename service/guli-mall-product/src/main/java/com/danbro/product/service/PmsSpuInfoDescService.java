package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsSpuInfoDescVo;
import com.danbro.product.entity.PmsSpuInfoDesc;

import java.util.List;


/**
 * spu信息介绍(PmsSpuInfoDesc)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
public interface PmsSpuInfoDescService extends IService<PmsSpuInfoDesc> {
    /**
     * 添加Spu介绍的图片，可以有多个图片
     *
     * @param images 介绍图片的数据
     * @param spuId  spuId
     * @return 添加结果
     */
    PmsSpuInfoDescVo saveSpuInfoDesc(List<String> images, Long spuId);

    /**
     * 获取 spu 的介绍
     *
     * @param spuId spuId
     * @return 介绍图片的对象
     */
    PmsSpuInfoDescVo getSpuDescBySpuId(Long spuId);
}