package com.danbro.order.mapper;
 
import com.danbro.order.entity.OmsOrderSetting;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 订单配置信息(OmsOrderSetting)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:30:18
 */
@Mapper
public interface OmsOrderSettingMapper extends BaseMapper<OmsOrderSetting>{
 
}