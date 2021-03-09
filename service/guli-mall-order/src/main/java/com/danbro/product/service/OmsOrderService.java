package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.OrderConfirmVo;
import com.danbro.product.entity.OmsOrder;

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

}