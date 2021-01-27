package com.danbro.coupon.mapper;
 
import com.danbro.coupon.entity.SmsCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 优惠券信息(SmsCoupon)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Mapper
public interface SmsCouponMapper extends BaseMapper<SmsCoupon>{
 
}