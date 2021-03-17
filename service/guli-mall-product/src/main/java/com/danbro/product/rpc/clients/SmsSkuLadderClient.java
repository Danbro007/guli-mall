package com.danbro.product.rpc.clients;


import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.SmsSkuLadderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "service-sms")
public interface SmsSkuLadderClient {
    @PostMapping("coupon/skuladder")
    ResultBean<SmsSkuLadderVo> insertSkuLadder(@RequestBody SmsSkuLadderVo smsSkuLadderVo);
}
