package com.danbro.coupon.service.impl;


import java.math.BigDecimal;
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
        // 只添加满足打折条件的数量和折扣数大于 0 的
        SmsSkuLadder smsSkuLadder = smsSkuLadderVo.convertToEntity();
        boolean save = this.save(smsSkuLadder);
        return MyCurdUtils.insertOrUpdate(smsSkuLadderVo.convertToVo(smsSkuLadder), save, ResponseCode.INSERT_FAILURE);
    }
}