package com.danbro.product.rpc.clients;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.SmsMemberPriceVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(name = "service-sms")
public interface SmsMemberPriceClient {
    @PostMapping("member/memberprice")
    ResultBean<SmsMemberPriceVo> insertMemberPrice(@RequestBody SmsMemberPriceVo smsMemberPriceVo);

    @PostMapping("member/memberprice/list")
    ResultBean<List<SmsMemberPriceVo>> batchInsertMemberPrice(@RequestBody List<SmsMemberPriceVo> memberPriceVoList);
}
