package com.danbro.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.coupon.controller.vo.SmsSpuBondsVo;
import com.danbro.coupon.entity.SmsSpuBounds;
import com.danbro.coupon.mapper.SmsSpuBoundsMapper;
import com.danbro.coupon.service.SmsSpuBoundsService;
import org.springframework.stereotype.Service;

/**
 * 商品spu积分设置(SmsSpuBounds)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsSpuBoundsServiceImpl extends ServiceImpl<SmsSpuBoundsMapper, SmsSpuBounds> implements SmsSpuBoundsService {

    @Override
    public SmsSpuBondsVo insertSpuBonds(SmsSpuBondsVo smsSpuBondsVo) {
        SmsSpuBounds smsSpuBounds = smsSpuBondsVo.convertToEntity();
        return MyCurdUtils.insertOrUpdate(smsSpuBondsVo.convertToVo(smsSpuBounds), this.save(smsSpuBounds), ResponseCode.INSERT_FAILURE);
    }
}