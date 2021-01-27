package com.danbro.order.entity;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:30:18
 */
@Data
@Accessors(chain = true)
@ApiModel("支付信息表")
public class OmsPaymentInfo implements Serializable {
    private static final long serialVersionUID = -57378821273993476L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("订单号（对外业务号）")
    @TableField("order_sn")
    private String orderSn;
    
                    @ApiModelProperty("订单id")
    @TableField("order_id")
    private Long orderId;
    
                    @ApiModelProperty("支付宝交易流水号")
    @TableField("alipay_trade_no")
    private String alipayTradeNo;
    
                    @ApiModelProperty("支付总金额")
    @TableField("total_amount")
    private Double totalAmount;
    
                    @ApiModelProperty("交易内容")
    @TableField("subject")
    private String subject;
    
                    @ApiModelProperty("支付状态")
    @TableField("payment_status")
    private String paymentStatus;
    
                    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("确认时间")
    @TableField("confirm_time")
    private Date confirmTime;
    
                    @ApiModelProperty("回调内容")
    @TableField("callback_content")
    private String callbackContent;
    
                    @ApiModelProperty("回调时间")
    @TableField("callback_time")
    private Date callbackTime;
    

}