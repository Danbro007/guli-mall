package com.danbro.coupon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.coupon.entity.SmsSeckillSkuRelation;
import org.apache.ibatis.annotations.Mapper;

/**
 * 秒杀活动商品关联(SmsSeckillSkuRelation)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Mapper
public interface SmsSeckillSkuRelationMapper extends BaseMapper<SmsSeckillSkuRelation> {

}