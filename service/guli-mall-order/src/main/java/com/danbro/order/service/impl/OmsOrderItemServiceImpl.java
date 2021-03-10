package com.danbro.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.order.entity.OmsOrderItem;
import com.danbro.order.mapper.OmsOrderItemMapper;
import com.danbro.order.service.OmsOrderItemService;
import org.springframework.stereotype.Service;

/**
 * 订单项信息(OmsOrderItem)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Service
public class OmsOrderItemServiceImpl extends ServiceImpl<OmsOrderItemMapper, OmsOrderItem> implements OmsOrderItemService {
    
}