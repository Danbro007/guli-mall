package com.danbro.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.order.entity.OmsPaymentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表(OmsPaymentInfo)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Mapper
public interface OmsPaymentInfoMapper extends BaseMapper<OmsPaymentInfo>{
 
}