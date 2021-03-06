package com.danbro.coupon.controller;

import com.danbro.coupon.service.SmsCouponSpuCategoryRelationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "优惠券分类关联(SmsCouponSpuCategoryRelation)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsCouponSpuCategoryRelation")
public class SmsCouponSpuCategoryRelationController {
    @Autowired
    private SmsCouponSpuCategoryRelationService smsCouponSpuCategoryRelationService;

}