package com.danbro.order.service;

import com.danbro.common.entity.ResultBean;
import com.danbro.order.controller.vo.UmsMemberReceiveAddressVo;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("通过会员ID查询出会员的所有地址")
    @GetMapping("member/address/list/{memberId}")
    ResultBean<List<UmsMemberReceiveAddressVo>> getMemberAddressListByMemberId(@PathVariable Long memberId);

    @ApiOperation("通过地址ID查找地址")
    @GetMapping("info/{addressId}")
    ResultBean<UmsMemberReceiveAddressVo> getMemberAddressByAddId(@PathVariable Long addressId);
}
