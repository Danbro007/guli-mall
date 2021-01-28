package com.danbro.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.coupon.entity.SmsSeckillSkuNotice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀商品通知订阅(SmsSeckillSkuNotice)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Mapper
public interface SmsSeckillSkuNoticeMapper extends BaseMapper<SmsSeckillSkuNotice> {

}