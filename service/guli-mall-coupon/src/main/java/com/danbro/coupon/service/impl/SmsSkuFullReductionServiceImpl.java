package com.danbro.coupon.service.impl;


import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.coupon.controller.vo.SmsSkuFullReductionVo;
import com.danbro.coupon.entity.SmsSkuFullReduction;
import com.danbro.coupon.mapper.SmsSkuFullReductionMapper;
import com.danbro.coupon.service.SmsSkuFullReductionService;
import org.springframework.stereotype.Service;

/**
 * 商品满减信息(SmsSkuFullReduction)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Service
public class SmsSkuFullReductionServiceImpl extends ServiceImpl<SmsSkuFullReductionMapper, SmsSkuFullReduction> implements SmsSkuFullReductionService {

    @Override
    public SmsSkuFullReductionVo insertSkuFullReduction(SmsSkuFullReductionVo smsSkuFullReductionVo) {
        SmsSkuFullReduction smsSkuFullReduction = smsSkuFullReductionVo.convertToEntity();
        boolean save = this.save(smsSkuFullReduction);
        return MyCurdUtils.insertOrUpdate(smsSkuFullReductionVo.convertToVo(smsSkuFullReduction), save, ResponseCode.INSERT_FAILURE);
    }
}