package com.danbro.order.entity;

import java.math.BigDecimal;
import java.util.Locale;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Data
@Accessors(chain = true)
@ApiModel("支付信息表")
public class OmsPaymentInfo implements Serializable {
    private static final long serialVersionUID = 150752710746878366L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("订单号（对外业务号）")
    private String orderSn;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("支付宝交易流水号")
    private String alipayTradeNo;

    @ApiModelProperty("支付总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty("交易内容")
    private String subject;

    @ApiModelProperty("支付状态")
    private String paymentStatus;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Locale createTime;

    @ApiModelProperty("确认时间")
    private Locale confirmTime;

    @ApiModelProperty("回调内容")
    private String callbackContent;

    @ApiModelProperty("回调时间")
    private Locale callbackTime;


}