package com.danbro.coupon.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.coupon.controller.vo.SmsSkuLadderVo;
import com.danbro.coupon.entity.SmsSkuLadder;
import com.danbro.coupon.service.SmsSkuLadderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
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
@Setter
@RequestMapping("coupon/skuladder")
public class SmsSkuLadderController {
    private SmsSkuLadderService smsSkuLadderService;

    @ApiOperation("添加商品阶梯价格")
    @PostMapping("")
    public ResultBean<SmsSkuLadderVo> insertSkuLadder(@Validated @RequestBody SmsSkuLadderVo smsSkuLadderVo){
        return ResultBean.ofSuccess(smsSkuLadderService.insertSkuLadder(smsSkuLadderVo));
    }
}