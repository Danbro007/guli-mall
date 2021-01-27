package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsSkuImages;
import com.danbro.product.service.PmsSkuImagesService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 22:02:43
 */
@Api(tags = "sku图片(PmsSkuImages)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsSkuImages")
public class PmsSkuImagesController {
    @Autowired
    private  PmsSkuImagesService pmsSkuImagesService;
 
}