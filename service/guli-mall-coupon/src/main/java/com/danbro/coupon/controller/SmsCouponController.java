package com.danbro.coupon.controller;
 
import com.danbro.coupon.entity.SmsCoupon;
import com.danbro.coupon.service.SmsCouponService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "优惠券信息(SmsCoupon)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsCoupon")
public class SmsCouponController {
    @Autowired
    private  SmsCouponService smsCouponService;
 
}