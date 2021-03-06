package com.danbro.coupon.controller;


import com.danbro.coupon.service.SmsHomeAdvService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "首页轮播广告(SmsHomeAdv)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsHomeAdv")
public class SmsHomeAdvController {
    @Autowired
    private SmsHomeAdvService smsHomeAdvService;

}