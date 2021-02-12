package com.danbro.coupon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.coupon.controller.vo.SmsSkuFullReductionVo;
import com.danbro.coupon.entity.SmsSkuFullReduction;


/**
 * 商品满减信息(SmsSkuFullReduction)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
public interface SmsSkuFullReductionService extends IService<SmsSkuFullReduction> {
    /**
     * 添加满减信息
     *
     * @param smsSkuFullReductionVo 要添加的满减信息
     * @return 添加完毕后的满减信息
     */
    SmsSkuFullReductionVo insertSkuFullReduction(SmsSkuFullReductionVo smsSkuFullReductionVo);

}