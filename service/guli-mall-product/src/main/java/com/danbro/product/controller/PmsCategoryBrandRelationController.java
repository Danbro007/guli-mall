package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsCategoryBrandRelation;
import com.danbro.product.service.PmsCategoryBrandRelationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Api(tags = "品牌分类关联(PmsCategoryBrandRelation)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsCategoryBrandRelation")
public class PmsCategoryBrandRelationController {
    @Autowired
    private  PmsCategoryBrandRelationService pmsCategoryBrandRelationService;
 
}