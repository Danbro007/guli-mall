package com.danbro.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.ConvertUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.member.controller.vo.UmsMemberReceiveAddressVo;
import com.danbro.member.entity.UmsMemberReceiveAddress;
import com.danbro.member.mapper.UmsMemberReceiveAddressMapper;
import com.danbro.member.service.UmsMemberReceiveAddressService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员收货地址(UmsMemberReceiveAddress)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Service
public class UmsMemberReceiveAddressServiceImpl extends ServiceImpl<UmsMemberReceiveAddressMapper, UmsMemberReceiveAddress> implements UmsMemberReceiveAddressService {

    @Override
    public List<UmsMemberReceiveAddressVo> getAddressListByMemberId(Long memberId) {
        List<UmsMemberReceiveAddress> addressList = this.list(new QueryWrapper<UmsMemberReceiveAddress>().lambda().eq(UmsMemberReceiveAddress::getMemberId, memberId));
        return ConvertUtils.batchConvert(addressList, UmsMemberReceiveAddressVo.class);
    }

    @Override
    public UmsMemberReceiveAddressVo getAddressByAddressId(Long addressId) {
        UmsMemberReceiveAddress address = MyCurdUtils.select(this.getById(addressId), ResponseCode.NOT_FOUND);
        return ConvertUtils.convert(address, UmsMemberReceiveAddressVo.class);
    }
}