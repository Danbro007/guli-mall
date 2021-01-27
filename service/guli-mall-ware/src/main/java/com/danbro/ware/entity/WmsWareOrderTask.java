package com.danbro.ware.entity;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:31:11
 */
@Data
@Accessors(chain = true)
@ApiModel("库存工作单")
public class WmsWareOrderTask implements Serializable {
    private static final long serialVersionUID = 394612062779891095L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("order_id")
    @TableField("order_id")
    private Long orderId;
    
                    @ApiModelProperty("order_sn")
    @TableField("order_sn")
    private String orderSn;
    
                    @ApiModelProperty("收货人")
    @TableField("consignee")
    private String consignee;
    
                    @ApiModelProperty("收货人电话")
    @TableField("consignee_tel")
    private String consigneeTel;
    
                    @ApiModelProperty("配送地址")
    @TableField("delivery_address")
    private String deliveryAddress;
    
                    @ApiModelProperty("订单备注")
    @TableField("order_comment")
    private String orderComment;
    
                    @ApiModelProperty("付款方式【 1:在线付款 2:货到付款】")
    @TableField("payment_way")
    private Object paymentWay;
    
                    @ApiModelProperty("任务状态")
    @TableField("task_status")
    private Object taskStatus;
    
                    @ApiModelProperty("订单描述")
    @TableField("order_body")
    private String orderBody;
    
                    @ApiModelProperty("物流单号")
    @TableField("tracking_no")
    private String trackingNo;
    
                    @ApiModelProperty("create_time")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("仓库id")
    @TableField("ware_id")
    private Long wareId;
    
                    @ApiModelProperty("工作单备注")
    @TableField("task_comment")
    private String taskComment;
    

}