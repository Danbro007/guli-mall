package com.danbro.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.dto.UmsMemberVo;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.MyCollectionUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyRandomUtils;
import com.danbro.order.controller.vo.*;
import com.danbro.order.entity.OmsOrder;
import com.danbro.order.entity.OmsOrderItem;
import com.danbro.order.interceptor.LoginInterceptor;
import com.danbro.order.mapper.OmsOrderMapper;
import com.danbro.order.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 订单(OmsOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Service
public class OmsOrderServiceImpl extends ServiceImpl<OmsOrderMapper, OmsOrder> implements OmsOrderService {
    public static final String ORDER_TOKEN = "product:token:";

    /**
     * LUA脚本
     */
    public static final String LUA_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    @Autowired
    MemberFeignService memberFeignService;

    @Autowired
    CartFeignService cartFeignService;

    @Autowired
    ThreadPoolExecutor executor;

    @Autowired
    PmsFeignService pmsFeignService;

    @Autowired
    WmsFeignService wmsFeignService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    OmsOrderItemService orderItemService;

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
        // 实现幂等性的令牌
        String token = MyRandomUtils.randomUUID();
        redisTemplate.opsForValue().set(ORDER_TOKEN + memberVo.getId(), token, 30L, TimeUnit.MINUTES);
        orderConfirmVo.setOrderToken(token);
        return orderConfirmVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderToResponseVo createOrder(SubmitOrderVo orderVo) {
        OrderToResponseVo orderToResponseVo = new OrderToResponseVo();
        UmsMemberVo memberVo = LoginInterceptor.MEMBER_THREADLOCAL.get();
        String orderToken = orderVo.getOrderToken();
        String key = ORDER_TOKEN + memberVo.getId();
        // 原子删除token
        Long result = redisTemplate.execute(new DefaultRedisScript<>(LUA_SCRIPT, Long.class), Collections.singletonList(key), orderToken);
        // 删除成功，执行创建订单的操作
        if (result != null && result == 1L) {
            // 创建订单
            OrderToResponseVo responseVo = buildOrder(orderVo, memberVo, orderToResponseVo);
            // 验价
            validPrice(responseVo);
            // 锁库存
            // 验价成功
            if (responseVo.getPayPrice().compareTo(orderToResponseVo.getPayPrice()) == 0) {
                this.save(orderToResponseVo.getOrder());
                orderItemService.saveBatch(orderToResponseVo.getItems());
                Boolean lockResult = lockStocks(orderToResponseVo);
                if (!lockResult) {
                    throw new GuliMallException(ResponseCode.LOCK_STOCK_FAILURE);
                }
                return responseVo;
            }
            throw new GuliMallException(ResponseCode.VALID_PRICE_FAILURE);
        }
        // 删除失败
        return null;
    }

    /**
     * 锁商品的库存
     *
     * @param orderToResponseVo 订单的信息
     * @return 锁库存的结果
     */
    private Boolean lockStocks(OrderToResponseVo orderToResponseVo) {
        ResultBean<List<WmsLockStockResultVo>> result = wmsFeignService.lockStock(orderToResponseVo.getItems());
        return result.getSuccess();
    }

    /**
     * 商品验价
     *
     * @param responseVo
     */
    private void validPrice(OrderToResponseVo responseVo) {
        List<OmsOrderItem> items = responseVo.getItems();
        BigDecimal total = BigDecimal.ZERO;
        // 优惠券优惠的总金额
        BigDecimal couponAmountTotal = BigDecimal.ZERO;
        // 积分优惠的总金额
        BigDecimal integrationAmountTotal = BigDecimal.ZERO;
        // 促销优惠的总金额
        BigDecimal promotionAmountTotal = BigDecimal.ZERO;
        // 赠送的积分总和
        Integer giftIntegrationTotal = 0;
        // 赠送的成长值总和
        Integer giftGrowthTotal = 0;
        // 计算出总的促销优惠金额、积分优惠的金额、优惠券优惠的金额、积分、成长值
        for (OmsOrderItem item : items) {
            couponAmountTotal = couponAmountTotal.add(item.getCouponAmount());
            integrationAmountTotal = integrationAmountTotal.add(item.getIntegrationAmount());
            promotionAmountTotal = promotionAmountTotal.add(item.getPromotionAmount());
            giftIntegrationTotal += item.getGiftIntegration();
            giftGrowthTotal += item.getGiftGrowth();
        }
        // total = 商品金额 - 促销优惠金额 - 积分优惠金额 - 优惠券优惠金额
        total = total.subtract(couponAmountTotal).subtract(integrationAmountTotal).subtract(promotionAmountTotal);
        OmsOrder order = responseVo.getOrder();
        // 应付金额 = 商品应付金额 + 运费
        order.setPayAmount(total.add(order.getFreightAmount()));
        responseVo.setPayPrice(order.getPayAmount());
        // 整个订单的优惠金额和成长值、积分
        order.setPromotionAmount(promotionAmountTotal).
                setCouponAmount(couponAmountTotal).
                setIntegrationAmount(integrationAmountTotal).setIntegration(giftIntegrationTotal).setGrowth(giftGrowthTotal);
    }

    /**
     * 创建订单对象
     *
     * @param orderVo  提交的订单信息
     * @param memberVo 会员信息
     */
    private OrderToResponseVo buildOrder(SubmitOrderVo orderVo, UmsMemberVo memberVo, OrderToResponseVo orderToResponseVo) {
        OmsOrder order = new OmsOrder();
        // 1、订单里的会员信息
        order.setMemberId(memberVo.getId()).setMemberUsername(memberVo.getUsername());
        // 生成订单号
        order.setOrderSn(MyRandomUtils.snowFlakeId());
        // 2、查询出用户地址
        FareVo fareVo = MyCurdUtils.rpcResultHandle(wmsFeignService.getFare(orderVo.getAddrId()));
        buildAddress(order, fareVo.getAddress());
        // 3、封装运费
        order.setFreightAmount(fareVo.getFare());
        //4、构建订单项
        List<CartItemVo> cartItemList = MyCurdUtils.rpcResultHandle(cartFeignService.getCartItemList(memberVo.getId()));
        if (MyCollectionUtils.isNotEmpty(cartItemList)) {
            List<OmsOrderItem> omsOrderItems = buildOrderItem(order, cartItemList);
            orderToResponseVo.setItems(omsOrderItems);
        }
        orderToResponseVo.setOrder(order).setFare(fareVo.getFare());
        return orderToResponseVo;
    }

    /**
     * 封装商品到订单项
     *
     * @param order        订单对象
     * @param cartItemList 要封装到订单里的商品
     * @return 订单项列表
     */
    private List<OmsOrderItem> buildOrderItem(OmsOrder order, List<CartItemVo> cartItemList) {
        return cartItemList.stream().map(item -> {
            OmsOrderItem orderItem = new OmsOrderItem();
            // 1、封装sku信息
            PmsSkuInfoVo pmsSkuInfoVo = MyCurdUtils.rpcResultHandle(pmsFeignService.getSkuInfo(item.getSkuId()));
            orderItem.setCategoryId(pmsSkuInfoVo.getCatalogId()).
                    setSkuPic(pmsSkuInfoVo.getSkuDefaultImg()).
                    setSkuPrice(item.getPrice()).
                    setSkuName(pmsSkuInfoVo.getSkuName()).
                    setSkuQuantity(item.getCount()).
                    setSkuAttrsVals(StringUtils.collectionToDelimitedString(item.getSkuAttr(), ";")).
                    setOrderSn(order.getOrderSn()).
                    setSkuId(item.getSkuId());
            // 2、封装spu信息
            PmsSpuInfoVo pmsSpuInfoVo = MyCurdUtils.rpcResultHandle(pmsFeignService.getSpuInfoBySpuId(pmsSkuInfoVo.getSpuId()));
            orderItem.setSpuName(pmsSpuInfoVo.getSpuName()).setSpuId(pmsSpuInfoVo.getId());
            // 3、封装品牌信息
            PmsBrandVo pmsBrandVo = MyCurdUtils.rpcResultHandle(pmsFeignService.getBrandInfo(pmsSpuInfoVo.getBrandId()));
            orderItem.setSpuBrand(pmsBrandVo.getName());
            // 4、订单号
            orderItem.setOrderSn(order.getOrderSn());
            // 5、积分、成长值
            orderItem.setGiftGrowth(item.getPrice().multiply(new BigDecimal(item.getCount())).intValue());
            orderItem.setGiftIntegration(item.getPrice().multiply(new BigDecimal(item.getCount())).intValue());
            // 6、优惠信息 这里我都设置为 0
            orderItem.setCouponAmount(BigDecimal.ZERO);
            orderItem.setPromotionAmount(BigDecimal.ZERO);
            orderItem.setIntegrationAmount(BigDecimal.ZERO);
            // 7、实际支付的金额（要减去优惠的金额）
            BigDecimal realAmount = item.getPrice().multiply(new BigDecimal(item.getCount())).
                    subtract(orderItem.getCouponAmount()).
                    subtract(orderItem.getIntegrationAmount()).
                    subtract(orderItem.getPromotionAmount());
            orderItem.setRealAmount(realAmount);
            return orderItem;
        }).collect(Collectors.toList());
    }

    /**
     * 构建订单的地址
     *
     * @param order     订单对象
     * @param addressVo 地址对象
     */
    private void buildAddress(OmsOrder order, UmsMemberReceiveAddressVo addressVo) {
        order.setReceiverCity(addressVo.getCity()).
                setReceiverDetailAddress(addressVo.getDetailAddress()).
                setReceiverName(addressVo.getName()).
                setReceiverProvince(addressVo.getProvince()).
                setReceiverPhone(addressVo.getPhone()).
                setReceiverPostCode(addressVo.getPostCode()).
                setReceiverRegion(addressVo.getRegion());
    }
}