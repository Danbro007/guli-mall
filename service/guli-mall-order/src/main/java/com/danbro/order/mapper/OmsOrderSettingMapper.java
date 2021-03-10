package com.danbro.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.order.entity.OmsOrderSetting;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单配置信息(OmsOrderSetting)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Mapper
public interface OmsOrderSettingMapper extends BaseMapper<OmsOrderSetting>{
 
}