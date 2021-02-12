package com.danbro.coupon.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.coupon.controller.vo.SmsSkuLadderVo;
import com.danbro.coupon.entity.SmsSkuLadder;
import com.danbro.coupon.mapper.SmsSkuLadderMapper;
import com.danbro.coupon.service.SmsSkuLadderService;
import org.springframework.stereotype.Service;

/**
 * 商品阶梯价格(SmsSkuLadder)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsSkuLadderServiceImpl extends ServiceImpl<SmsSkuLadderMapper, SmsSkuLadder> implements SmsSkuLadderService {

    @Override
    public SmsSkuLadderVo insertSkuLadder(SmsSkuLadderVo smsSkuLadderVo) {
        SmsSkuLadder smsSkuLadder = smsSkuLadderVo.convertToEntity();
        System.out.println(smsSkuLadder);
        boolean save = this.save(smsSkuLadder);
        return MyCurdUtils.insertOrUpdate(smsSkuLadderVo.convertToVo(smsSkuLadder), save, ResponseCode.INSERT_FAILURE);
    }
}