package com.danbro.product.controller;

import com.danbro.product.service.PmsProductAttrValueService;
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
@Api(tags = "spu属性值(PmsProductAttrValue)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsProductAttrValue")
public class PmsProductAttrValueController {
    @Autowired
    private  PmsProductAttrValueService pmsProductAttrValueService;
 
}