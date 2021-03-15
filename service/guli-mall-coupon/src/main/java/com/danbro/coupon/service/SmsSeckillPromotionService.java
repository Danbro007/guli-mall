package com.danbro.coupon.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.coupon.controller.vo.SmsSeckillPromotionVo;
import com.danbro.coupon.entity.SmsSeckillPromotion;


/**
 * 秒杀活动(SmsSeckillPromotion)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
public interface SmsSeckillPromotionService extends IService<SmsSeckillPromotion> {
    /**
     * 分页查询秒杀活动
     *
     * @param pageParam 查询条件
     * @param key       关键字
     * @return 查询结果
     */
    Pagination<SmsSeckillPromotionVo, SmsSeckillPromotion> queryPage(PageParam<SmsSeckillPromotion> pageParam, String key);

    SmsSeckillPromotionVo insert(SmsSeckillPromotionVo smsSeckillPromotionVo);

    SmsSeckillPromotionVo update(SmsSeckillPromotionVo smsSeckillPromotionVo);

    void batchDeletePromotion(Long[] ids);
}