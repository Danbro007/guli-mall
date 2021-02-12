package com.danbro.product.rpc.clients;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.SmsSpuBondsVo;
import com.danbro.product.rpc.fallbacks.SmsSpuBondFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Classname SmsSpuBondsClient
 * @Description TODO
 * @Date 2021/2/11 22:08
 * @Created by Administrator
 */
@Component
@FeignClient(name = "service-sms", fallback = SmsSpuBondFallback.class)
public interface SmsSpuBondsClient {
    @PostMapping("coupon/spubounds")
    ResultBean<SmsSpuBondsVo> insertSpuBonds(@RequestBody SmsSpuBondsVo smsSpuBondsVo);
}
