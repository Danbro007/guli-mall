package com.danbro.order.service;


import java.util.concurrent.ExecutionException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.dto.SecKillSkuDto;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.order.controller.vo.OmsOrderVo;
import com.danbro.order.controller.vo.OrderConfirmVo;
import com.danbro.order.controller.vo.OrderToResponseVo;
import com.danbro.order.controller.vo.PayAsyncVo;
import com.danbro.order.controller.vo.PayVo;
import com.danbro.order.controller.vo.SubmitOrderVo;
import com.danbro.order.entity.OmsOrder;


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


    PayVo getPayInfoByOrderSn(String orderSn);

    /**
     * 分页查询用户的订单
     *
     * @param pageParam 分页条件
     * @param key       关键字
     * @return 查询结果
     */
    Pagination<OmsOrderVo, OmsOrder> queryPageOrder(PageParam<OmsOrder> pageParam, String key);

    /**
     * 处理支付宝支付成功的结果
     *
     * @param payAsyncVo
     */
    void handlerPayResult(PayAsyncVo payAsyncVo);

    void saveSecKillOrder(SecKillSkuDto secKillSkuDto);
}