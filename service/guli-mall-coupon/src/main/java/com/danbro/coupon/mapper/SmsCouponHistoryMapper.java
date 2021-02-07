package com.danbro.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.coupon.entity.SmsCouponHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录(SmsCouponHistory)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Mapper
public interface SmsCouponHistoryMapper extends BaseMapper<SmsCouponHistory> {

}