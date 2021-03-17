package com.danbro.seckill.service;

import com.danbro.common.entity.ResultBean;
import com.danbro.seckill.vo.PmsSkuInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Danrbo
 * @Classname PmsFeignService
 * @Description TODO
 * @Date 2021/3/16 13:24
 */
@Component
@FeignClient(value = "service-pms")
public interface PmsFeignService {
    @ApiOperation("查询sku的详细信息")
    @GetMapping("product/skuinfo/info/{skuId}")
    ResultBean<PmsSkuInfoVo> getSkuInfo(@PathVariable Long skuId);
}
