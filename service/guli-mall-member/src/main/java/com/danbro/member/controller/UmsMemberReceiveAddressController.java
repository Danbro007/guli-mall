package com.danbro.member.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.member.controller.vo.UmsMemberReceiveAddressVo;
import com.danbro.member.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Api(tags = "会员收货地址(UmsMemberReceiveAddress)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("member/address")
public class UmsMemberReceiveAddressController {
    @Autowired
    private UmsMemberReceiveAddressService umsMemberReceiveAddressService;


    @ApiOperation("查找会员所有的收货地址")
    @GetMapping("list/{memberId}")
    public ResultBean<List<UmsMemberReceiveAddressVo>> getMemberAddressList(@PathVariable Long memberId) {
        return ResultBean.ofSuccess(umsMemberReceiveAddressService.getAddressListByMemberId(memberId));
    }

    @ApiOperation("通过地址ID查找地址")
    @GetMapping("info/{addressId}")
    public ResultBean<UmsMemberReceiveAddressVo> getMemberAddressByAddId(@PathVariable Long addressId) {
        return ResultBean.ofSuccess(umsMemberReceiveAddressService.getAddressByAddressId(addressId));
    }
}