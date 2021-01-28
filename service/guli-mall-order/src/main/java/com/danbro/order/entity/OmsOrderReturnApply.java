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
@ApiModel("订单退货申请")
public class OmsOrderReturnApply implements Serializable {
    private static final long serialVersionUID = 564261316689507248L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("order_id")
    private Long orderId;

    @ApiModelProperty("退货商品id")
    private Long skuId;

    @ApiModelProperty("订单编号")
    private String orderSn;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("申请时间")
    private Locale createTime;

    @ApiModelProperty("会员用户名")
    private String memberUsername;

    @ApiModelProperty("退款金额")
    private BigDecimal returnAmount;

    @ApiModelProperty("退货人姓名")
    private String returnName;

    @ApiModelProperty("退货人电话")
    private String returnPhone;

    @ApiModelProperty("申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]")
    private Integer status;

    @ApiModelProperty("处理时间")
    private Locale handleTime;

    @ApiModelProperty("商品图片")
    private String skuImg;

    @ApiModelProperty("商品名称")
    private String skuName;

    @ApiModelProperty("商品品牌")
    private String skuBrand;

    @ApiModelProperty("商品销售属性(JSON)")
    private String skuAttrsVals;

    @ApiModelProperty("退货数量")
    private Integer skuCount;

    @ApiModelProperty("商品单价")
    private BigDecimal skuPrice;

    @ApiModelProperty("商品实际支付单价")
    private BigDecimal skuRealPrice;

    @ApiModelProperty("原因")
    private String reason;

    @ApiModelProperty("描述")
    private String description述;

    @ApiModelProperty("凭证图片，以逗号隔开")
    private String descPics;

    @ApiModelProperty("处理备注")
    private String handleNote;

    @ApiModelProperty("处理人员")
    private String handleMan;

    @ApiModelProperty("收货人")
    private String receiveMan;

    @ApiModelProperty("收货时间")
    private Locale receiveTime;

    @ApiModelProperty("收货备注")
    private String receiveNote;

    @ApiModelProperty("收货电话")
    private String receivePhone;

    @ApiModelProperty("公司收货地址")
    private String companyAddress;


}