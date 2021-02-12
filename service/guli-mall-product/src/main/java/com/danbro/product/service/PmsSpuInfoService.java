package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsSpuInfoVo;
import com.danbro.product.controller.vo.spu.Spu;
import com.danbro.product.entity.PmsSpuInfo;


/**
 * spu信息(PmsSpuInfo)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
public interface PmsSpuInfoService extends IService<PmsSpuInfo> {
    /**
     * 添加 Spu 信息
     *
     * @param spuInfoVo spu信息
     */
    void insertSpuInfo(PmsSpuInfoVo spuInfoVo);

}