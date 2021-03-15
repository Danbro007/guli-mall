package com.danbro.order.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.order.controller.vo.OmsOrderVo;
import com.danbro.order.controller.vo.OrderConfirmVo;
import com.danbro.order.controller.vo.OrderToResponseVo;
import com.danbro.order.controller.vo.SubmitOrderVo;
import com.danbro.order.entity.OmsOrder;

import java.util.concurrent.ExecutionException;


/**
 * 订单(OmsOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
public interface OmsOrderService extends IService<OmsOrder> {

    /**
     * 返回要创建的订单
     *
     * @return 订单对象
     */
    OrderConfirmVo createConfirmOrder() throws ExecutionException, InterruptedException;

    /**
     * 创建订单
     *
     * @param orderVo 订单的信息
     */
    OrderToResponseVo createOrder(SubmitOrderVo orderVo);

    /**
     * 通过 orderSs 获取订单信息
     *
     * @param orderSn 订单编号
     * @return 订单信息
     */
    OmsOrderVo getOrderInfoByOrderSn(String orderSn);

    /**
     * 关闭订单
     *
     * @param order 订单
     */
    void closeOrder(OmsOrder order);

}