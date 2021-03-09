package com.danbro.cart.service;

import com.danbro.cart.controller.vo.PmsSkuInfoVo;
import com.danbro.common.entity.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Danrbo
 * @Classname PmsFeignService
 * @Description TODO
 * @Date 2021/3/9 12:50
 */
@Component
@FeignClient(value = "service-pms")
public interface PmsFeignService {

    @ApiOperation("通过skuId获取商品信息")
    @GetMapping("product/skuinfo/info/{skuId}")
    ResultBean<PmsSkuInfoVo> getSkuInfo(@PathVariable Long skuId);
}
