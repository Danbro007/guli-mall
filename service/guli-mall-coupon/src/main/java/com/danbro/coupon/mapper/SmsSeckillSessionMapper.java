package com.danbro.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.coupon.entity.SmsSeckillSession;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动场次(SmsSeckillSession)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Mapper
public interface SmsSeckillSessionMapper extends BaseMapper<SmsSeckillSession> {

}