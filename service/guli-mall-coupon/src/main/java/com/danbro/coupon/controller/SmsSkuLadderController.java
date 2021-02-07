package com.danbro.coupon.controller;

import com.danbro.coupon.service.SmsSkuLadderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "商品阶梯价格(SmsSkuLadder)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSkuLadder")
public class SmsSkuLadderController {
    @Autowired
    private SmsSkuLadderService smsSkuLadderService;

}