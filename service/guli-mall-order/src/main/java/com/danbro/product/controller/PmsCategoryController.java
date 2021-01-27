package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsCategory;
import com.danbro.product.service.PmsCategoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Api(tags = "商品三级分类(PmsCategory)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsCategory")
public class PmsCategoryController {
    @Autowired
    private  PmsCategoryService pmsCategoryService;
 
}