package com.danbro.product.controller;

import com.danbro.product.service.PmsCategoryBrandRelationService;
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
@Api(tags = "品牌分类关联(PmsCategoryBrandRelation)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsCategoryBrandRelation")
public class PmsCategoryBrandRelationController {
    @Autowired
    private  PmsCategoryBrandRelationService pmsCategoryBrandRelationService;
 
}