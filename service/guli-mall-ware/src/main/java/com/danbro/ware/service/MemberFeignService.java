package com.danbro.ware.service;

import com.danbro.common.entity.ResultBean;
import com.danbro.ware.controller.vo.UmsMemberReceiveAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Danrbo
 * @Classname MemberFeignService
 * @Description TODO
 * @Date 2021/3/9 15:15
 */
@Component
@FeignClient(value = "service-ums")
public interface MemberFeignService {
    @GetMapping("member/address/info/{addressId}")
    ResultBean<UmsMemberReceiveAddressVo> getAddressInfoByAddressId(@PathVariable Long addressId);
}
