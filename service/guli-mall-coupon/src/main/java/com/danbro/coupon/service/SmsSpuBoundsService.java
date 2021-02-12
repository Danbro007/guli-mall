package com.danbro.coupon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.coupon.controller.vo.SmsSpuBondsVo;
import com.danbro.coupon.entity.SmsSpuBounds;


/**
 * 商品spu积分设置(SmsSpuBounds)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
public interface SmsSpuBoundsService extends IService<SmsSpuBounds> {
    /**
     * 添加商品Spu积分
     *
     * @param smsSpuBondsVo 商品Spu积分数据
     * @return 添加完毕后的商品Spu积分数据
     */
    SmsSpuBondsVo insertSpuBonds(SmsSpuBondsVo smsSpuBondsVo);
}