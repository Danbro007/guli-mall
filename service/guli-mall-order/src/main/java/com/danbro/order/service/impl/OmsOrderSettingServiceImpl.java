package com.danbro.order.service.impl;
 
import com.danbro.order.entity.OmsOrderSetting;
import com.danbro.order.mapper.OmsOrderSettingMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.order.service.OmsOrderSettingService;
import org.springframework.stereotype.Service;
 
/**
 * 订单配置信息(OmsOrderSetting)表服务实现类
 *
 * @author makejava
 * @since 2021-01-27 21:30:18
 */
@Service
public class OmsOrderSettingServiceImpl extends ServiceImpl<OmsOrderSettingMapper, OmsOrderSetting> implements OmsOrderSettingService {
    
}