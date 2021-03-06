package com.danbro.cart.service;

import com.danbro.cart.controller.vo.CartItemVo;
import com.danbro.cart.controller.vo.CartVo;

/**
 * @author Danrbo
 * @Classname CartService
 * @Description TODO 购物车服务
 * @Date 2021/3/6 15:35
 */
public interface CartService {

    /**
     * 添加商品到购物车
     *
     * @param skuId skuId
     * @param num   商品数量
     */
    CartItemVo addToCart(Long skuId, Integer num);

    /**
     * 获取用户的购物车
     *
     * @return 购物车商品
     */
    CartVo getCartList();

    /**
     * 通过 skuId 查询到用户购物车里的指定商品
     *
     * @param skuId skuId
     * @return 查询到的商品
     */
    CartItemVo getCartItem(Long skuId);
}
