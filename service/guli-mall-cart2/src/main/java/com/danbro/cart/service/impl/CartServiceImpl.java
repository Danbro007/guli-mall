package com.danbro.cart.service.impl;

import com.danbro.cart.controller.dto.UserInfoDto;
import com.danbro.cart.controller.vo.CartItemVo;
import com.danbro.cart.controller.vo.CartVo;
import com.danbro.cart.controller.vo.PmsSkuInfoVo;
import com.danbro.cart.interceptor.CartInterceptor;
import com.danbro.cart.service.CartService;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyCurdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Danrbo
 * @Classname CartServiceImpl
 * @Description TODO
 * @Date 2021/3/6 15:38
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    PmsService pmsService;

    private static final String MEMBER_CART_PREFIX = "member:cart:";

    @Override
    public CartItemVo addToCart(Long skuId, Integer num) {
        String cartKey = getCartKey();
        BoundHashOperations<String, Object, Object> hashOperations = getBoundHashOps(cartKey);
        // 判断要添加购物车的商品原来的购物车里有没有
        CartItemVo cartItemFromCache = (CartItemVo) hashOperations.get(skuId);
        // 没有则创建一个 CartItem 对象
        CartItemVo cartItemVo;
        if (cartItemFromCache == null) {
            // rpc 查询到 sku 的价格、标题、销售属性等信息
            PmsSkuInfoVo pmsSkuInfoVo = MyCurdUtils.rpcResultHandle(pmsService.getSkuInfo(skuId));
            // 封装成例如：颜色：黑色，内存：128G
            List<String> attrValueList = pmsSkuInfoVo.getAttr().stream().map(attr -> attr.getAttrName() + ":" + attr.getAttrValue()).collect(Collectors.toList());
            cartItemVo = buildCartItem(skuId, num, pmsSkuInfoVo, attrValueList);
            hashOperations.put(skuId, cartItemVo);
        } else {
            // 原来的购物车里有就在原来的数量上添加
            cartItemVo = cartItemFromCache.setCount(cartItemFromCache.getCount() + num);
            hashOperations.put(skuId, cartItemFromCache);
        }
        return cartItemVo;
    }

    /**
     * 获取购物车的用户key
     *
     * @return key
     */
    private String getCartKey() {
        UserInfoDto userInfoDto = CartInterceptor.threadLocal.get();
        // 用户购物车的key
        String cartKey;
        // 登录用户
        if (userInfoDto.getId() != null) {
            cartKey = MEMBER_CART_PREFIX + userInfoDto.getId().toString();
        }
        // 游客用户
        else {
            cartKey = MEMBER_CART_PREFIX + userInfoDto.getUserKey();
        }
        return cartKey;
    }

    @Override
    public CartVo getCartList() {
        String cartKey = getCartKey();
        BoundHashOperations<String, Object, Object> boundHashOps = getBoundHashOps(cartKey);
        Map<Object, Object> entries = boundHashOps.entries();
        CartVo cartVo = new CartVo();
        // 购物车为空
        if (entries != null && !entries.isEmpty()) {
            ArrayList<CartItemVo> cartItemVos = new ArrayList<>();
            entries.values().stream().map(o -> cartItemVos.add((CartItemVo) o)).collect(Collectors.toList());
            cartVo.setItems(cartItemVos);
        }
        return cartVo;
    }

    @Override
    public CartItemVo getCartItem(Long skuId) {
        String cartKey = getCartKey();
        BoundHashOperations<String, Object, Object> boundHashOps = getBoundHashOps(cartKey);
        return (CartItemVo) boundHashOps.get(skuId);
    }

    /**
     * 把查询到的信息封装成 CartItem 对象
     *
     * @param skuId         skuId
     * @param num           商品的数量
     * @param pmsSkuInfoVo  查询到 sku 信息
     * @param attrValueList 销售属性列表
     * @return CartItem 对象
     */
    private CartItemVo buildCartItem(Long skuId, Integer num, PmsSkuInfoVo pmsSkuInfoVo, List<String> attrValueList) {
        return new CartItemVo().setSkuId(skuId).
                setCount(num).
                setCheck(true).
                setImage(pmsSkuInfoVo.getSkuDefaultImg()).
                setPrice(pmsSkuInfoVo.getPrice()).setTitle(pmsSkuInfoVo.getSkuTitle()).
                setSkuAttr(attrValueList);
    }

    /**
     * 获取 key 的hash操作对象
     *
     * @param cartKey 缓存的 key
     * @return key的操作对象
     */
    private BoundHashOperations<String, Object, Object> getBoundHashOps(String cartKey) {
        return redisTemplate.boundHashOps(cartKey);
    }

}
