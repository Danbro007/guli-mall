package com.danbro.product.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Danrbo
 * @Classname OrderConfirmVo
 * @Description TODO 订单确认 Vo
 * @Date 2021/3/9 12:23
 */
@Data
public class OrderConfirmVo {
    @ApiModelProperty("会员地址列表")
    private List<UmsMemberReceiveAddressVo> memberAddressVos;

    @ApiModelProperty("购物车里选中的商品列表")
    private List<CartItemVo> items;

    @ApiModelProperty("优惠券（会员积分）")
    private Integer integration;

    @ApiModelProperty("防止重复提交的令牌")
    private String orderToken;

    @ApiModelProperty("订单总价")
    private BigDecimal totalPrice;

    @ApiModelProperty("商品总件数")
    private Integer count;

    @ApiModelProperty("实际支付的价格")
    private BigDecimal payPrice;
}
