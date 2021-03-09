package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.dto.UmsMemberVo;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.product.controller.vo.CartItemVo;
import com.danbro.product.controller.vo.OrderConfirmVo;
import com.danbro.product.controller.vo.UmsMemberReceiveAddressVo;
import com.danbro.product.entity.OmsOrder;
import com.danbro.product.interceptor.LoginInterceptor;
import com.danbro.product.mapper.OmsOrderMapper;
import com.danbro.product.service.CartFeignService;
import com.danbro.product.service.MemberFeignService;
import com.danbro.product.service.OmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 订单(OmsOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {
    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    CartFeignService cartFeignService;

    @Autowired
    ThreadPoolExecutor executor;


    @Override
    public OrderConfirmVo createConfirmOrder() throws ExecutionException, InterruptedException {
        OrderConfirmVo orderConfirmVo = new OrderConfirmVo();
        UmsMemberVo memberVo = LoginInterceptor.MEMBER_THREADLOCAL.get();
        CompletableFuture<Void> addressFuture = CompletableFuture.runAsync(() -> {
            // 1、异步查找会员的收货地址
            List<UmsMemberReceiveAddressVo> addressVoList = MyCurdUtils.rpcResultHandle(memberFeignService.getMemberAddressListByMemberId(memberVo.getId()));
            if (MyCollectionUtils.isNotEmpty(addressVoList)) {
                orderConfirmVo.setMemberAddressVos(addressVoList);
            }
        }, executor);
        // 2、异步查找购物车里的商品信息并更新价格
        CompletableFuture<Void> cartItemListFuture = CompletableFuture.runAsync(() -> {
            List<CartItemVo> cartItemList = MyCurdUtils.rpcResultHandle(cartFeignService.getCartItemList(memberVo.getId()));
            orderConfirmVo.setItems(cartItemList);
            // 商品总件数
            Integer count = 0;
            for (CartItemVo cartItemVo : cartItemList) {
                count += cartItemVo.getCount();
            }
            orderConfirmVo.setCount(count);
        }, executor);
        CompletableFuture.allOf(addressFuture, cartItemListFuture).get();
        // 3、 获取会员的积分
        orderConfirmVo.setIntegration(memberVo.getIntegration());
        // 4、订单里商品的总价
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartItemVo cartItemVo : orderConfirmVo.getItems()) {
            totalPrice = totalPrice.add(cartItemVo.getTotalPrice());
        }
        orderConfirmVo.setPayPrice(totalPrice);
        orderConfirmVo.setTotalPrice(totalPrice);
        return orderConfirmVo;
    }
}