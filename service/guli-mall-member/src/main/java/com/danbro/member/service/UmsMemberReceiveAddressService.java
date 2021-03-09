package com.danbro.member.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.member.controller.vo.UmsMemberReceiveAddressVo;
import com.danbro.member.entity.UmsMemberReceiveAddress;

import java.util.List;


/**
 * 会员收货地址(UmsMemberReceiveAddress)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
public interface UmsMemberReceiveAddressService extends IService<UmsMemberReceiveAddress> {
    /**
     * 返回会员的所有收货地址
     *
     * @param memberId 会员ID
     * @return 收货地址列表
     */
    List<UmsMemberReceiveAddressVo> getAddressListByMemberId(Long memberId);

    /**
     * 通过地址ID获取地址信息
     *
     * @param addressId 地址ID
     * @return 地址信息
     */
    UmsMemberReceiveAddressVo getAddressByAddressId(Long addressId);
}