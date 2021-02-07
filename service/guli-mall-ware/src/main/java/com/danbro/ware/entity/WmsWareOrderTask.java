package com.danbro.ware.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Data
@Accessors(chain = true)
@ApiModel("库存工作单")
public class WmsWareOrderTask implements Serializable {
    private static final long serialVersionUID = -65163248918908529L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("order_id")
    private Long orderId;

    @ApiModelProperty("order_sn")
    private String orderSn;

    @ApiModelProperty("收货人")
    private String consignee;

    @ApiModelProperty("收货人电话")
    private String consigneeTel;

    @ApiModelProperty("配送地址")
    private String deliveryAddress;

    @ApiModelProperty("订单备注")
    private String orderComment;

    @ApiModelProperty("付款方式【 1:在线付款 2:货到付款】")
    private Integer paymentWay;

    @ApiModelProperty("任务状态")
    private Integer taskStatus;

    @ApiModelProperty("订单描述")
    private String orderBody;

    @ApiModelProperty("物流单号")
    private String trackingNo;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("create_time")
    private Date createTime;

    @ApiModelProperty("仓库id")
    private Long wareId;

    @ApiModelProperty("工作单备注")
    private String taskComment;


}