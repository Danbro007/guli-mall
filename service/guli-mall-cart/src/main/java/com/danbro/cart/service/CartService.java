package com.danbro.cart.service;

import com.danbro.cart.controller.vo.CartItemVo;
import com.danbro.cart.controller.vo.CartVo;

import java.util.List;

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

    /**
     * 合并购物车
     */
    CartVo mergeCart();

    /**
     * 修改购物车里的商品选中状态
     * @param skuId skuId
     * @param check 商品的选中状态
     */
    void checkItem(Long skuId, Boolean check);

    /**
     * 修改购物车里商品的数量
     * @param skuId 商品ID
     * @param num 商品数量
     */
    void countItem(Long skuId, Integer num);

    /**
     * 删除购物车里的商品
     *
     * @param skuId
     */
    void deleteItem(Long skuId);

    /**
     * 获取会员购物车里选中商品的信息（价格是最新的）
     *
     * @param memberId 会员ID
     * @return 购物车里的商品信息
     */
    List<CartItemVo> getCartItemList(Long memberId);
}
