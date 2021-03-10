package com.danbro.order.service;

import com.danbro.common.entity.ResultBean;
import com.danbro.order.controller.vo.PmsBrandVo;
import com.danbro.order.controller.vo.PmsSkuInfoVo;
import com.danbro.order.controller.vo.PmsSpuInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Danrbo
 * @Classname PmsFeignService
 * @Description TODO
 * @Date 2021/3/10 10:49
 */
@Component
@FeignClient(value = "service-pms")
public interface PmsFeignService {
    /**
     * 通过skuId获取商品信息
     *
     * @param skuId skuID
     * @return
     */
    @ApiOperation("通过skuId获取商品信息")
    @GetMapping("product/skuinfo/info/{skuId}")
    ResultBean<PmsSkuInfoVo> getSkuInfo(@PathVariable Long skuId);

    /**
     * 查询spu信息
     *
     * @param spuId spuId
     * @return
     */
    @ApiOperation("查询spu信息")
    @GetMapping("product/spuinfo/{spuId}}")
    ResultBean<PmsSpuInfoVo> getSpuInfoBySpuId(@PathVariable Long spuId);


    /**
     * 获取品牌的详细信息
     *
     * @param brandId brandId
     * @return 品牌信息
     */
    @ApiOperation("获取品牌的详细信息")
    @GetMapping("product/brand/info/{brandId}")
    ResultBean<PmsBrandVo> getBrandInfo(@PathVariable Long brandId);
}

