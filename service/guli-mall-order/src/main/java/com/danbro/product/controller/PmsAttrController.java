package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsAttr;
import com.danbro.product.service.PmsAttrService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:43
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