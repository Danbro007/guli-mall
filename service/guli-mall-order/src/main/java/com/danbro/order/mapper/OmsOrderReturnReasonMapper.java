package com.danbro.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.order.entity.OmsOrderReturnReason;
import org.apache.ibatis.annotations.Mapper;

/**
 * 退货原因(OmsOrderReturnReason)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Mapper
public interface OmsOrderReturnReasonMapper extends BaseMapper<OmsOrderReturnReason>{
 
}