package com.danbro.order.entity;
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
@ApiModel("退款信息")
public class OmsRefundInfo implements Serializable {
    private static final long serialVersionUID = 313704776197511380L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("退款的订单")
    @TableField("order_return_id")
    private Long orderReturnId;
    
                    @ApiModelProperty("退款金额")
    @TableField("refund")
    private Double refund;
    
                    @ApiModelProperty("退款交易流水号")
    @TableField("refund_sn")
    private String refundSn;
    
                    @ApiModelProperty("退款状态")
    @TableField("refund_status")
    private Object refundStatus;
    
                    @ApiModelProperty("退款渠道[1-支付宝，2-微信，3-银联，4-汇款]")
    @TableField("refund_channel")
    private Object refundChannel;
    
                    @ApiModelProperty("$column.comment")
    @TableField("refund_content")
    private String refundContent;
    

}