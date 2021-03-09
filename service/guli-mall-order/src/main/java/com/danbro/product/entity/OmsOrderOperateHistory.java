package com.danbro.product.entity;

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
 * @since 2021-01-28 18:50:27
 */
@Data
@Accessors(chain = true)
@ApiModel("订单操作历史记录")
public class OmsOrderOperateHistory implements Serializable {
    private static final long serialVersionUID = -98948484581165340L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("操作人[用户；系统；后台管理员]")
    private String operateMan;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("操作时间")
    private Date createTime;

    @ApiModelProperty("订单状态【0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单】")
    private Integer orderStatus;

    @ApiModelProperty("备注")
    private String note;


}