package com.danbro.coupon.controller;

import com.danbro.coupon.service.SmsSeckillSkuNoticeService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "秒杀商品通知订阅(SmsSeckillSkuNotice)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSeckillSkuNotice")
public class SmsSeckillSkuNoticeController {
    @Autowired
    private SmsSeckillSkuNoticeService smsSeckillSkuNoticeService;

}