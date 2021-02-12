package com.danbro.coupon.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.coupon.controller.vo.SmsSkuFullReductionVo;
import com.danbro.coupon.entity.SmsSkuFullReduction;
import com.danbro.coupon.service.SmsSkuFullReductionService;
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
@Api(tags = "商品满减信息(SmsSkuFullReduction)")
@Validated
@RestController
@AllArgsConstructor
@Setter
@RequestMapping("coupon/skufullreduction")
public class SmsSkuFullReductionController {
    private SmsSkuFullReductionService smsSkuFullReductionService;

    @ApiOperation("添加满减信息")
    @PostMapping("")
    public ResultBean<SmsSkuFullReductionVo> insertSkuFullReduction(@Validated @RequestBody SmsSkuFullReductionVo smsSkuFullReductionVo){
        return ResultBean.ofSuccess(smsSkuFullReductionService.insertSkuFullReduction(smsSkuFullReductionVo));
    }
}