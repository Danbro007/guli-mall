package com.danbro.coupon.controller;

import com.danbro.coupon.service.SmsSeckillPromotionService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "秒杀活动(SmsSeckillPromotion)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSeckillPromotion")
public class SmsSeckillPromotionController {
    @Autowired
    private SmsSeckillPromotionService smsSeckillPromotionService;

}