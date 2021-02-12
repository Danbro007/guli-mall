package com.danbro.coupon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.coupon.controller.vo.SmsSkuLadderVo;
import com.danbro.coupon.entity.SmsSkuLadder;


/**
 * 商品阶梯价格(SmsSkuLadder)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
public interface SmsSkuLadderService extends IService<SmsSkuLadder> {
    /**
     * 添加商品阶梯价格
     *
     * @param smsSkuLadderVo 商品阶梯价格
     * @return 添加完毕后的商品阶梯价格
     */
    SmsSkuLadderVo insertSkuLadder(SmsSkuLadderVo smsSkuLadderVo);
}