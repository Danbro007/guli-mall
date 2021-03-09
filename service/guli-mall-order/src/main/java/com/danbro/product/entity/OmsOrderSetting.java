package com.danbro.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author makejava
 * @since 2021-01-28 18:50:27
 */
@Data
@Accessors(chain = true)
@ApiModel("订单配置信息")
public class OmsOrderSetting implements Serializable {
    private static final long serialVersionUID = 345219619951453337L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("秒杀订单超时关闭时间(分)")
    private Integer flashOrderOvertime;

    @ApiModelProperty("正常订单超时时间(分)")
    private Integer normalOrderOvertime;

    @ApiModelProperty("发货后自动确认收货时间（天）")
    private Integer confirmOvertime;

    @ApiModelProperty("自动完成交易时间，不能申请退货（天）")
    private Integer finishOvertime;

    @ApiModelProperty("订单完成后自动好评时间（天）")
    private Integer commentOvertime;

    @ApiModelProperty("会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】")
    private Integer memberLevel;


}