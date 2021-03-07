package com.danbro.order.rpc.clients;


import com.danbro.common.entity.ResultBean;
import com.danbro.order.controller.vo.SmsSkuLadderVo;
import com.danbro.order.rpc.fallbacks.SmsSkuLadderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "service-sms",fallback = SmsSkuLadderFallback.class)
public interface SmsSkuLadderClient {
    @PostMapping("coupon/skuladder")
    ResultBean<SmsSkuLadderVo> insertSkuLadder(@RequestBody SmsSkuLadderVo smsSkuLadderVo);
}
