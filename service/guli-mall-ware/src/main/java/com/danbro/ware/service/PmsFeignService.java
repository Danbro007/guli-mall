package com.danbro.ware.service;

import com.danbro.common.entity.ResultBean;
import com.danbro.ware.controller.vo.PmsSkuInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname PmsSkuInfoClient
 * @Description TODO
 * @Date 2021/2/15 10:46
 * @Created by Administrator
 */
@Component
@FeignClient(name = "service-pms")
public interface PmsFeignService {
    /**
     * 获取sku的详细信息
     *
     * @param skuId skuID
     * @return sku的详细信息
     */
    @GetMapping("product/skuinfo/info/{skuId}")
    ResultBean<PmsSkuInfoVo> getSkuInfo(@PathVariable Long skuId);
}
