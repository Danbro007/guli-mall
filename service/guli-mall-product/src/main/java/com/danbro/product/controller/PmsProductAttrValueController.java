package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsProductAttrValue;
import com.danbro.product.service.PmsProductAttrValueService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 22:02:43
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