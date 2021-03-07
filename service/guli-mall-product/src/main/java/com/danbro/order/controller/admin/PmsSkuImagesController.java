package com.danbro.order.controller.admin;

import com.danbro.order.service.PmsSkuImagesService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author makejava
 * @since 2021-01-28 18:56:54
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