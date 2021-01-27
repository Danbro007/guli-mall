package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsSkuSaleAttrValue;
import com.danbro.product.service.PmsSkuSaleAttrValueService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Api(tags = "sku销售属性&值(PmsSkuSaleAttrValue)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsSkuSaleAttrValue")
public class PmsSkuSaleAttrValueController {
    @Autowired
    private  PmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;
 
}