package com.danbro.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.coupon.entity.SmsSeckillPromotion;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动(SmsSeckillPromotion)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Mapper
public interface SmsSeckillPromotionMapper extends BaseMapper<SmsSeckillPromotion> {

}