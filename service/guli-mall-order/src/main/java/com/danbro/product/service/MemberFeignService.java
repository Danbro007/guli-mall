package com.danbro.product.service;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.UmsMemberReceiveAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Danrbo
 * @Classname MemberFeignService
 * @Description TODO
 * @Date 2021/3/9 12:31
 */
@Component
@FeignClient(value = "service-ums")
public interface MemberFeignService {
    @GetMapping("member/address/list/{memberId}")
    ResultBean<List<UmsMemberReceiveAddressVo>> getMemberAddressListByMemberId(@PathVariable Long memberId);
}
