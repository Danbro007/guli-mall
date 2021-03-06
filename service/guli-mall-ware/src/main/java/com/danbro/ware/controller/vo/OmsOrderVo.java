package com.danbro.ware.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Data
@Accessors(chain = true)
@ApiModel("订单")
@AllArgsConstructor
@NoArgsConstructor
public class OmsOrderVo implements Serializable {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("member_id")
    private Long memberId;

    @ApiModelProperty("订单号")
    private String orderSn;

    @ApiModelProperty("使用的优惠券")
    private Long couponId;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("create_time")
    private Date createTime;

    @ApiModelProperty("用户名")
    private String memberUsername;

    @ApiModelProperty("订单总额")
    private BigDecimal totalAmount;

    @ApiModelProperty("应付总额")
    private BigDecimal payAmount;

    @ApiModelProperty("运费金额")
    private BigDecimal freightAmount;

    @ApiModelProperty("促销优化金额（促销价、满减、阶梯价）")
    private BigDecimal promotionAmount;

    @ApiModelProperty("积分抵扣金额")
    private BigDecimal integrationAmount;

    @ApiModelProperty("优惠券抵扣金额")
    private BigDecimal couponAmount;

    @ApiModelProperty("后台调整订单使用的折扣金额")
    private BigDecimal discountAmount;

    @ApiModelProperty("支付方式【1->支付宝；2->微信；3->银联； 4->货到付款；】")
    private Integer payType;

    @ApiModelProperty("订单来源[0->PC订单；1->app订单]")
    private Integer sourceType;

    @ApiModelProperty("订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
    private Integer status;

    @ApiModelProperty("物流公司(配送方式)")
    private String deliveryCompany;

    @ApiModelProperty("物流单号")
    private String deliverySn;

    @ApiModelProperty("自动确认时间（天）")
    private Integer autoConfirmDay;

    @ApiModelProperty("可以获得的积分")
    private Integer integration;

    @ApiModelProperty("可以获得的成长值")
    private Integer growth;

    @ApiModelProperty("发票类型[0->不开发票；1->电子发票；2->纸质发票]")
    private Integer billType;

    @ApiModelProperty("发票抬头")
    private String billHeader;

    @ApiModelProperty("发票内容")
    private String billContent;

    @ApiModelProperty("收票人电话")
    private String billReceiverPhone;

    @ApiModelProperty("收票人邮箱")
    private String billReceiverEmail;

    @ApiModelProperty("收货人姓名")
    private String receiverName;

    @ApiModelProperty("收货人电话")
    private String receiverPhone;

    @ApiModelProperty("收货人邮编")
    private String receiverPostCode;

    @ApiModelProperty("省份/直辖市")
    private String receiverProvince;

    @ApiModelProperty("城市")
    private String receiverCity;

    @ApiModelProperty("区")
    private String receiverRegion;

    @ApiModelProperty("详细地址")
    private String receiverDetailAddress;

    @ApiModelProperty("订单备注")
    private String note;

    @ApiModelProperty("确认收货状态[0->未确认；1->已确认]")
    private Boolean confirmStatus;

    @ApiModelProperty("删除状态【0->未删除；1->已删除】")
    private Boolean deleteStatus;

    @ApiModelProperty("下单时使用的积分")
    private Integer useIntegration;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("支付时间")
    private Date paymentTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("发货时间")
    private Date deliveryTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("确认收货时间")
    private Date receiveTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("评价时间")
    private Date commentTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("修改时间")
    private Date modifyTime;

}