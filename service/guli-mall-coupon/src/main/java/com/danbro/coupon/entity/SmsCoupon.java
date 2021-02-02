package com.danbro.coupon.entity;

import java.math.BigDecimal;
import java.util.Locale;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Data
@Accessors(chain = true)
@ApiModel("优惠券信息")
public class SmsCoupon implements Serializable {
    private static final long serialVersionUID = 113875114384225850L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
    private Integer couponType;

    @ApiModelProperty("优惠券图片")
    private String couponImg;

    @ApiModelProperty("优惠卷名字")
    private String couponName;

    @ApiModelProperty("数量")
    private Integer num;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("每人限领张数")
    private Integer perLimit;

    @ApiModelProperty("使用门槛")
    private BigDecimal minPoint;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("使用类型[0->全场通用；1->指定分类；2->指定商品]")
    private Integer useType;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("发行数量")
    private Integer publishCount;

    @ApiModelProperty("已使用数量")
    private Integer useCount;

    @ApiModelProperty("领取数量")
    private Integer receiveCount;

    @ApiModelProperty("可以领取的开始日期")
    private Date enableStartTime;

    @ApiModelProperty("可以领取的结束日期")
    private Date enableEndTime;

    @ApiModelProperty("优惠码")
    private String code;

    @ApiModelProperty("可以领取的会员等级[0->不限等级，其他-对应等级]")
    private Integer memberLevel;

    @ApiModelProperty("发布状态[0-未发布，1-已发布]")
    private Boolean publish;


}