package com.danbro.product.rpc.clients;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.SmsSkuFullReductionVo;
import com.danbro.product.rpc.fallbacks.SmsSkuFullReductionFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "service-sms",fallback = SmsSkuFullReductionFallback.class)
public interface SmsSkuFullReductionClient {

    @PostMapping("coupon/skufullreduction")
    ResultBean<SmsSkuFullReductionVo> insertSkuFullReduction(@RequestBody SmsSkuFullReductionVo smsSkuFullReductionVo);
}
