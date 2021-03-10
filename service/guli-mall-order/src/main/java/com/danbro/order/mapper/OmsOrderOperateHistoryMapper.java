package com.danbro.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.order.entity.OmsOrderOperateHistory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录(OmsOrderOperateHistory)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Mapper
public interface OmsOrderOperateHistoryMapper extends BaseMapper<OmsOrderOperateHistory>{
 
}