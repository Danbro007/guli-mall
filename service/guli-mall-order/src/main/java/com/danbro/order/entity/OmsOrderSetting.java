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
@ApiModel("订单配置信息")
public class OmsOrderSetting implements Serializable {
    private static final long serialVersionUID = -23117549398402906L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("秒杀订单超时关闭时间(分)")
    @TableField("flash_order_overtime")
    private Integer flashOrderOvertime;
    
                    @ApiModelProperty("正常订单超时时间(分)")
    @TableField("normal_order_overtime")
    private Integer normalOrderOvertime;
    
                    @ApiModelProperty("发货后自动确认收货时间（天）")
    @TableField("confirm_overtime")
    private Integer confirmOvertime;
    
                    @ApiModelProperty("自动完成交易时间，不能申请退货（天）")
    @TableField("finish_overtime")
    private Integer finishOvertime;
    
                    @ApiModelProperty("订单完成后自动好评时间（天）")
    @TableField("comment_overtime")
    private Integer commentOvertime;
    
                    @ApiModelProperty("会员等级【0-不限会员等级，全部通用；其他-对应的其他会员等级】")
    @TableField("member_level")
    private Object memberLevel;
    

}