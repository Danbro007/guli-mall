package com.danbro.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Data
@Accessors(chain = true)
@ApiModel("退款信息")
public class OmsRefundInfo implements Serializable {
    private static final long serialVersionUID = 156256624317542112L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("退款的订单")
    private Long orderReturnId;

    @ApiModelProperty("退款金额")
    private BigDecimal refund;

    @ApiModelProperty("退款交易流水号")
    private String refundSn;

    @ApiModelProperty("退款状态")
    private Integer refundStatus;

    @ApiModelProperty("退款渠道[1-支付宝，2-微信，3-银联，4-汇款]")
    private Integer refundChannel;

    @ApiModelProperty("退款内容")
    private String refundContent;


}