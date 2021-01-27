package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsSkuInfo;
import com.danbro.product.service.PmsSkuInfoService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Api(tags = "sku信息(PmsSkuInfo)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsSkuInfo")
public class PmsSkuInfoController {
    @Autowired
    private  PmsSkuInfoService pmsSkuInfoService;
 
}