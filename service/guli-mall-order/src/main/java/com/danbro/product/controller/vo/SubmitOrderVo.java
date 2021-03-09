package com.danbro.product.controller.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Danrbo
 * @Classname SubmitOrderVo
 * @Description TODO 体检的订单对象
 * @Date 2021/3/9 16:35
 */
@Data
public class SubmitOrderVo {
    private Long addrId;
    private BigDecimal payPrice;
    private String orderToken;
}
