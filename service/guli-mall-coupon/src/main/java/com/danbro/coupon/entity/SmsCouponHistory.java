package com.danbro.coupon.entity;

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
 * @since 2021-01-28 19:09:33
 */
@Data
@Accessors(chain = true)
@ApiModel("优惠券领取历史记录")
public class SmsCouponHistory implements Serializable {
    private static final long serialVersionUID = -26262180468140515L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("优惠券id")
    private Long couponId;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("会员名字")
    private String memberNickName;

    @ApiModelProperty("获取方式[0->后台赠送；1->主动领取]")
    private Integer getType;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("使用状态[0->未使用；1->已使用；2->已过期]")
    private Integer useType;

    @ApiModelProperty("使用时间")
    private Date useTime;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("订单号")
    private Long orderSn;


}