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
@ApiModel("订单")
public class OmsOrder implements Serializable {
    private static final long serialVersionUID = -57679849979819209L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("member_id")
    @TableField("member_id")
    private Long memberId;
    
                    @ApiModelProperty("订单号")
    @TableField("order_sn")
    private String orderSn;
    
                    @ApiModelProperty("使用的优惠券")
    @TableField("coupon_id")
    private Long couponId;
    
                    @ApiModelProperty("create_time")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("用户名")
    @TableField("member_username")
    private String memberUsername;
    
                    @ApiModelProperty("订单总额")
    @TableField("total_amount")
    private Double totalAmount;
    
                    @ApiModelProperty("应付总额")
    @TableField("pay_amount")
    private Double payAmount;
    
                    @ApiModelProperty("运费金额")
    @TableField("freight_amount")
    private Double freightAmount;
    
                    @ApiModelProperty("促销优化金额（促销价、满减、阶梯价）")
    @TableField("promotion_amount")
    private Double promotionAmount;
    
                    @ApiModelProperty("积分抵扣金额")
    @TableField("integration_amount")
    private Double integrationAmount;
    
                    @ApiModelProperty("优惠券抵扣金额")
    @TableField("coupon_amount")
    private Double couponAmount;
    
                    @ApiModelProperty("后台调整订单使用的折扣金额")
    @TableField("discount_amount")
    private Double discountAmount;
    
                    @ApiModelProperty("支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】")
    @TableField("pay_type")
    private Object payType;
    
                    @ApiModelProperty("订单来源[0->PC订单；1->app订单]")
    @TableField("source_type")
    private Object sourceType;
    
                    @ApiModelProperty("订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
    @TableField("status")
    private Object status;
    
                    @ApiModelProperty("物流公司(配送方式)")
    @TableField("delivery_company")
    private String deliveryCompany;
    
                    @ApiModelProperty("物流单号")
    @TableField("delivery_sn")
    private String deliverySn;
    
                    @ApiModelProperty("自动确认时间（天）")
    @TableField("auto_confirm_day")
    private Integer autoConfirmDay;
    
                    @ApiModelProperty("可以获得的积分")
    @TableField("integration")
    private Integer integration;
    
                    @ApiModelProperty("可以获得的成长值")
    @TableField("growth")
    private Integer growth;
    
                    @ApiModelProperty("发票类型[0->不开发票；1->电子发票；2->纸质发票]")
    @TableField("bill_type")
    private Object billType;
    
                    @ApiModelProperty("发票抬头")
    @TableField("bill_header")
    private String billHeader;
    
                    @ApiModelProperty("发票内容")
    @TableField("bill_content")
    private String billContent;
    
                    @ApiModelProperty("收票人电话")
    @TableField("bill_receiver_phone")
    private String billReceiverPhone;
    
                    @ApiModelProperty("收票人邮箱")
    @TableField("bill_receiver_email")
    private String billReceiverEmail;
    
                    @ApiModelProperty("收货人姓名")
    @TableField("receiver_name")
    private String receiverName;
    
                    @ApiModelProperty("收货人电话")
    @TableField("receiver_phone")
    private String receiverPhone;
    
                    @ApiModelProperty("收货人邮编")
    @TableField("receiver_post_code")
    private String receiverPostCode;
    
                    @ApiModelProperty("省份/直辖市")
    @TableField("receiver_province")
    private String receiverProvince;
    
                    @ApiModelProperty("城市")
    @TableField("receiver_city")
    private String receiverCity;
    
                    @ApiModelProperty("区")
    @TableField("receiver_region")
    private String receiverRegion;
    
                    @ApiModelProperty("详细地址")
    @TableField("receiver_detail_address")
    private String receiverDetailAddress;
    
                    @ApiModelProperty("订单备注")
    @TableField("note")
    private String note;
    
                    @ApiModelProperty("确认收货状态[0->未确认；1->已确认]")
    @TableField("confirm_status")
    private Object confirmStatus;
    
                    @ApiModelProperty("删除状态【0->未删除；1->已删除】")
    @TableField("delete_status")
    private Object deleteStatus;
    
                    @ApiModelProperty("下单时使用的积分")
    @TableField("use_integration")
    private Integer useIntegration;
    
                    @ApiModelProperty("支付时间")
    @TableField("payment_time")
    private Date paymentTime;
    
                    @ApiModelProperty("发货时间")
    @TableField("delivery_time")
    private Date deliveryTime;
    
                    @ApiModelProperty("确认收货时间")
    @TableField("receive_time")
    private Date receiveTime;
    
                    @ApiModelProperty("评价时间")
    @TableField("comment_time")
    private Date commentTime;
    
                    @ApiModelProperty("修改时间")
    @TableField("modify_time")
    private Date modifyTime;
    

}