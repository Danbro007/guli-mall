package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.product.service.PmsAttrGroupService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 22:02:42
 */
@Api(tags = "属性分组(PmsAttrGroup)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsAttrGroup")
public class PmsAttrGroupController {
    @Autowired
    private  PmsAttrGroupService pmsAttrGroupService;
 
}