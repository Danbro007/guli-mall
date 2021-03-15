package com.danbro.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.order.controller.vo.OmsOrderItemVo;
import com.danbro.order.entity.OmsOrderItem;

import java.util.List;


/**
 * 订单项信息(OmsOrderItem)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
public interface OmsOrderItemService extends IService<OmsOrderItem> {
    List<OmsOrderItemVo> getOrderItemListByOrderSn(String orderSn);
}