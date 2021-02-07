package com.danbro.coupon.controller;

import com.danbro.coupon.entity.SmsCouponHistory;
import com.danbro.coupon.service.SmsCouponHistoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "优惠券领取历史记录(SmsCouponHistory)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsCouponHistory")
public class SmsCouponHistoryController {
    @Autowired
    private SmsCouponHistoryService smsCouponHistoryService;

}