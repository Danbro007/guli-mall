package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsBrand;
import com.danbro.product.service.PmsBrandService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 22:02:42
 */
@Api(tags = "品牌(PmsBrand)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsBrand")
public class PmsBrandController {
    @Autowired
    private  PmsBrandService pmsBrandService;
 
}