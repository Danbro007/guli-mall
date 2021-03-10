package com.danbro.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.order.entity.OmsOrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息(OmsOrderItem)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Mapper
public interface OmsOrderItemMapper extends BaseMapper<OmsOrderItem> {

}