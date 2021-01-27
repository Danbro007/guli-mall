package com.danbro.order.service.impl;
 
import com.danbro.order.entity.OmsOrder;
import com.danbro.order.mapper.OmsOrderMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.order.service.OmsOrderService;
import org.springframework.stereotype.Service;
 
/**
 * 订单(OmsOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-01-27 21:30:18
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {
    
}