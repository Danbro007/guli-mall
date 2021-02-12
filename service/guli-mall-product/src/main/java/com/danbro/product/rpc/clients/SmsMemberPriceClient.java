package com.danbro.product.rpc.clients;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.SmsMemberPriceVo;
import com.danbro.product.rpc.fallbacks.SmsMemberPriceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "service-sms",fallback = SmsMemberPriceFallback.class)
public interface SmsMemberPriceClient {
    @PostMapping("member/memberprice")
    ResultBean<SmsMemberPriceVo> insertMemberPrice(@RequestBody SmsMemberPriceVo smsMemberPriceVo);

    @PostMapping("member/memberprice/list")
    ResultBean<List<SmsMemberPriceVo>> batchInsertMemberPrice(@RequestBody List<SmsMemberPriceVo> memberPriceVoList);
}
