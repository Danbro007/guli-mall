package com.danbro.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.coupon.entity.SmsMemberPrice;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格(SmsMemberPrice)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Mapper
public interface SmsMemberPriceMapper extends BaseMapper<SmsMemberPrice> {

}