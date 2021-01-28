package com.danbro.coupon.controller;

import com.danbro.coupon.service.SmsSeckillSessionService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "秒杀活动场次(SmsSeckillSession)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSeckillSession")
public class SmsSeckillSessionController {
    @Autowired
    private SmsSeckillSessionService smsSeckillSessionService;

}