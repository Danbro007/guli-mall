package com.danbro.coupon.controller;


import com.danbro.coupon.service.SmsCouponSpuRelationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "优惠券与产品关联(SmsCouponSpuRelation)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsCouponSpuRelation")
public class SmsCouponSpuRelationController {
    @Autowired
    private SmsCouponSpuRelationService smsCouponSpuRelationService;

}