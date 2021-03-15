package com.danbro.order.controller.vo;

/**
 * @author Danrbo
 * @Classname PayVo
 * @Description TODO
 * @Date 2021/3/15 12:29
 */

import lombok.Data;

@Data
public class PayVo {
    private String out_trade_no; // 商户订单号 必填
    private String subject; // 订单名称 必填
    private String total_amount;  // 付款金额 必填
    private String body; // 商品描述 可空
}
