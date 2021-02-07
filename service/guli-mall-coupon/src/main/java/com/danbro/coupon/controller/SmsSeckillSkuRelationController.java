package com.danbro.coupon.controller;

import com.danbro.coupon.service.SmsSeckillSkuRelationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "秒杀活动商品关联(SmsSeckillSkuRelation)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSeckillSkuRelation")
public class SmsSeckillSkuRelationController {
    @Autowired
    private SmsSeckillSkuRelationService smsSeckillSkuRelationService;

}