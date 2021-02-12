package com.danbro.coupon.rpc.clients;

import com.danbro.common.entity.ResultBean;
import com.danbro.coupon.controller.vo.UmsMemberLevelVo;
import com.danbro.coupon.rpc.fallbacks.UmsMemberLevelFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname SmsMemberPriceClient
 * @Description TODO
 * @Date 2021/2/12 16:06
 * @Created by Administrator
 */
@Component
@FeignClient(name = "service-ums", fallback = UmsMemberLevelFallback.class)
public interface UmsMemberLevelClient {

    @GetMapping("/member/memberlevel/info/{memberLevelName}")
    ResultBean<UmsMemberLevelVo> getMemberLevelInfoByName(@PathVariable String memberLevelName);
}
