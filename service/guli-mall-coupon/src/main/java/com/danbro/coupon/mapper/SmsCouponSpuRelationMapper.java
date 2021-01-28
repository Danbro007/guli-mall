package com.danbro.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.coupon.entity.SmsCouponSpuRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联(SmsCouponSpuRelation)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Mapper
public interface SmsCouponSpuRelationMapper extends BaseMapper<SmsCouponSpuRelation> {

}