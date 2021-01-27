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
@ApiModel("订单操作历史记录")
public class OmsOrderOperateHistory implements Serializable {
    private static final long serialVersionUID = -22986853691409465L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("订单id")
    @TableField("order_id")
    private Long orderId;
    
                    @ApiModelProperty("操作人[用户；系统；后台管理员]")
    @TableField("operate_man")
    private String operateMan;
    
                    @ApiModelProperty("操作时间")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
    @TableField("order_status")
    private Object orderStatus;
    
                    @ApiModelProperty("备注")
    @TableField("note")
    private String note;
    

}