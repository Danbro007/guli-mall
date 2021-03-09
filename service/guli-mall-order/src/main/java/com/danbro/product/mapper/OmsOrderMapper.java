package com.danbro.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.product.entity.OmsOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单(OmsOrder)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Mapper
public interface OmsOrderMapper extends BaseMapper<OmsOrder>{
 
}