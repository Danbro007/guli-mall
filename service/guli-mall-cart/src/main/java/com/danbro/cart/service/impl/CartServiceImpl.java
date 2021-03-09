package com.danbro.cart.service.impl;

import com.danbro.cart.controller.dto.UserInfoDto;
import com.danbro.cart.controller.vo.CartItemVo;
import com.danbro.cart.controller.vo.CartVo;
import com.danbro.cart.controller.vo.PmsSkuInfoVo;
import com.danbro.cart.interceptor.CartInterceptor;
import com.danbro.cart.service.CartService;
import com.danbro.cart.service.PmsFeignService;
import com.danbro.common.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
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
    PmsFeignService pmsService;

    private static final String MEMBER_CART_PREFIX = "member:cart:";

    @Override
    public CartItemVo addToCart(Long skuId, Integer num) {
        String cartKey = getCartKey();
        BoundHashOperations<String, Object, Object> hashOperations = getBoundHashOps(cartKey);
        // 判断要添加购物车的商品原来的购物车里有没有
        CartItemVo cartItemFromCache = getValueFromRedis(hashOperations.get(skuId.toString()), CartItemVo.class);
        // 没有则创建一个 CartItem 对象
        CartItemVo cartItemVo;
        if (cartItemFromCache == null) {
            // rpc 查询到 sku 的价格、标题、销售属性等信息
            PmsSkuInfoVo pmsSkuInfoVo = MyCurdUtils.rpcResultHandle(pmsService.getSkuInfo(skuId));
            // 封装成例如：颜色：黑色，内存：128G
            if (MyCollectionUtils.isNotEmpty(pmsSkuInfoVo.getAttr())) {
                List<String> attrValueList = pmsSkuInfoVo.getAttr().stream().
                        map(attr -> attr.getAttrName() + ":" + attr.getAttrValue()).
                        collect(Collectors.toList());
                cartItemVo = buildCartItem(skuId, num, pmsSkuInfoVo);
                cartItemVo.setSkuAttr(attrValueList);
            } else {
                cartItemVo = buildCartItem(skuId, num, pmsSkuInfoVo);
            }
        } else {
            // 原来的购物车里有就在原来的数量上添加
            cartItemVo = cartItemFromCache.setCount(cartItemFromCache.getCount() + num);
        }
        hashOperations.put(skuId.toString(), MyJSONUtils.toJSONString(cartItemVo));
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
        return mergeCart();
    }

    @Override
    public CartItemVo getCartItem(Long skuId) {
        String cartKey = getCartKey();
        BoundHashOperations<String, Object, Object> boundHashOps = getBoundHashOps(cartKey);
        return getValueFromRedis(boundHashOps.get(skuId.toString()), CartItemVo.class);
    }

    @Override
    public CartVo mergeCart() {
        UserInfoDto userInfoDto = CartInterceptor.threadLocal.get();
        // 用户购物车的key
        String cartKey;
        CartVo cartVo = new CartVo();
        List<CartItemVo> cartItemList;
        // 登录用户
        if (userInfoDto.getId() != null) {
            cartKey = MEMBER_CART_PREFIX + userInfoDto.getId().toString();
            String tempCartKey = MEMBER_CART_PREFIX + userInfoDto.getUserKey();
            // 临时购物车里的东西
            List<CartItemVo> tempCartItemList = getCartItemList(tempCartKey);
            // 合并购物车
            if (MyCollectionUtils.isNotEmpty(tempCartItemList)) {
                tempCartItemList.forEach(cartItem -> addToCart(cartItem.getSkuId(), cartItem.getCount()));
            }
            // 清空临时购物车
            redisTemplate.delete(tempCartKey);
            cartItemList = getCartItemList(cartKey);
        }
        // 临时用户
        else {
            cartKey = MEMBER_CART_PREFIX + userInfoDto.getUserKey();
            cartItemList = getCartItemList(cartKey);
        }
        cartVo.setItems(cartItemList);
        return cartVo;

    }

    @Override
    public void checkItem(Long skuId, Boolean check) {
        String cartKey = getCartKey();
        BoundHashOperations<String, Object, Object> boundHashOps = getBoundHashOps(cartKey);
        CartItemVo cartItemVo = getCartItem(skuId);
        // 查找出商品修改选中状态
        if (MyObjectUtils.isNotNull(cartItemVo)) {
            cartItemVo.setCheck(check);
            boundHashOps.put(skuId.toString(), MyJSONUtils.toJSONString(cartItemVo));
        }
    }

    @Override
    public void countItem(Long skuId, Integer num) {
        String cartKey = getCartKey();
        BoundHashOperations<String, Object, Object> boundHashOps = getBoundHashOps(cartKey);
        CartItemVo cartItemVo = getCartItem(skuId);
        // 查找出商品修改数量
        if (MyObjectUtils.isNotNull(cartItemVo)) {
            cartItemVo.setCount(num);
            boundHashOps.put(skuId.toString(), MyJSONUtils.toJSONString(cartItemVo));
        }
    }

    @Override
    public void deleteItem(Long skuId) {
        String cartKey = getCartKey();
        BoundHashOperations<String, Object, Object> boundHashOps = getBoundHashOps(cartKey);
        boundHashOps.delete(skuId.toString());
    }

    @Override
    public List<CartItemVo> getCartItemList(Long memberId) {
        String cartKey = MEMBER_CART_PREFIX + memberId;
        List<CartItemVo> cartItemList = getCartItemList(cartKey);
        if (MyCollectionUtils.isNotEmpty(cartItemList)) {
            return cartItemList.stream().map(item -> {
                // 查询最新价格
                PmsSkuInfoVo pmsSkuInfoVo = MyCurdUtils.rpcResultHandle(pmsService.getSkuInfo(item.getSkuId()), false);
                if (MyObjectUtils.isNotNull(pmsSkuInfoVo)) {
                    item.setPrice(pmsSkuInfoVo.getPrice());
                }
                // 查询库存
                return item;
            }).filter(CartItemVo::getCheck).collect(Collectors.toList());
        }
        return null;

    }

    /**
     * 把查询到的信息封装成 CartItem 对象
     *
     * @param skuId        skuId
     * @param num          商品的数量
     * @param pmsSkuInfoVo 查询到 sku 信息
     * @return CartItem 对象
     */
    private CartItemVo buildCartItem(Long skuId, Integer num, PmsSkuInfoVo pmsSkuInfoVo) {
        return new CartItemVo().setSkuId(skuId).
                setCount(num).
                setCheck(true).
                setImage(pmsSkuInfoVo.getSkuDefaultImg()).
                setPrice(pmsSkuInfoVo.getPrice()).setTitle(pmsSkuInfoVo.getSkuTitle());
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

    /**
     * 通过 cartKey 获取购物车里所有的商品
     *
     * @param cartKey cartKey
     * @return 商品列表
     */
    private List<CartItemVo> getCartItemList(String cartKey) {
        BoundHashOperations<String, Object, Object> boundHashOps = getBoundHashOps(cartKey);
        if (MyMapUtils.isNotEmpty(boundHashOps.entries())) {
            // Todo
            return boundHashOps.entries().values().stream().map(o -> getValueFromRedis(o, CartItemVo.class)).collect(Collectors.toList());
        }
        return null;
    }

    private <T> T getValueFromRedis(Object obj, Class<T> clazz) {
        String json = (String) obj;
        return MyJSONUtils.parseObject(json, clazz);
    }


}
