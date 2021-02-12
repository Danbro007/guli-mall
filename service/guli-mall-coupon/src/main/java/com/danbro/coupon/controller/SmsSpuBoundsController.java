package com.danbro.coupon.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.coupon.controller.vo.SmsSpuBondsVo;
import com.danbro.coupon.entity.SmsSpuBounds;
import com.danbro.coupon.service.SmsSpuBoundsService;
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
@Api(tags = "商品spu积分设置(SmsSpuBounds)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("coupon/spubounds")
@Setter
public class SmsSpuBoundsController {
    private SmsSpuBoundsService smsSpuBoundsService;

    @ApiOperation("添加商品spu积分")
    @PostMapping("")
    public ResultBean<SmsSpuBondsVo> insertSpuBonds(@Validated @RequestBody SmsSpuBondsVo smsSpuBondsVo) {
        return ResultBean.ofSuccess(smsSpuBoundsService.insertSpuBonds(smsSpuBondsVo));
    }
}