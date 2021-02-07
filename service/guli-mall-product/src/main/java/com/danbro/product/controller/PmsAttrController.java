package com.danbro.product.controller;

import com.danbro.product.service.PmsAttrService;
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
@Api(tags = "商品属性(PmsAttr)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsAttr")
public class PmsAttrController {
    @Autowired
    private  PmsAttrService pmsAttrService;
 
}