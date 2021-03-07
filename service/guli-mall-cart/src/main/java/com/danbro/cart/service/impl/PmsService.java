package com.danbro.cart.service.impl;

import com.danbro.cart.controller.vo.PmsSkuInfoVo;
import com.danbro.common.entity.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Danrbo
 * @Classname PmsService
 * @Description TODO
 * @Date 2021/3/6 15:51
 */
@FeignClient(value = "service-pms")
public interface PmsService {
    @GetMapping("product/skuinfo/info/{skuId}")
    ResultBean<PmsSkuInfoVo> getSkuInfo(@PathVariable Long skuId);
}
