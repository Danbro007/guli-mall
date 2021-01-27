package com.danbro.coupon.entity;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Data
@Accessors(chain = true)
@ApiModel("优惠券信息")
public class SmsCoupon implements Serializable {
    private static final long serialVersionUID = -32787258528220289L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
    @TableField("coupon_type")
    private Object couponType;
    
                    @ApiModelProperty("优惠券图片")
    @TableField("coupon_img")
    private String couponImg;
    
                    @ApiModelProperty("优惠卷名字")
    @TableField("coupon_name")
    private String couponName;
    
                    @ApiModelProperty("数量")
    @TableField("num")
    private Integer num;
    
                    @ApiModelProperty("金额")
    @TableField("amount")
    private Double amount;
    
                    @ApiModelProperty("每人限领张数")
    @TableField("per_limit")
    private Integer perLimit;
    
                    @ApiModelProperty("使用门槛")
    @TableField("min_point")
    private Double minPoint;
    
                    @ApiModelProperty("开始时间")
    @TableField("start_time")
    private Date startTime;
    
                    @ApiModelProperty("结束时间")
    @TableField("end_time")
    private Date endTime;
    
                    @ApiModelProperty("使用类型[0->全场通用；1->指定分类；2->指定商品]")
    @TableField("use_type")
    private Object useType;
    
                    @ApiModelProperty("备注")
    @TableField("note")
    private String note;
    
                    @ApiModelProperty("发行数量")
    @TableField("publish_count")
    private Integer publishCount;
    
                    @ApiModelProperty("已使用数量")
    @TableField("use_count")
    private Integer useCount;
    
                    @ApiModelProperty("领取数量")
    @TableField("receive_count")
    private Integer receiveCount;
    
                    @ApiModelProperty("可以领取的开始日期")
    @TableField("enable_start_time")
    private Date enableStartTime;
    
                    @ApiModelProperty("可以领取的结束日期")
    @TableField("enable_end_time")
    private Date enableEndTime;
    
                    @ApiModelProperty("优惠码")
    @TableField("code")
    private String code;
    
                    @ApiModelProperty("可以领取的会员等级[0->不限等级，其他-对应等级]")
    @TableField("member_level")
    private Object memberLevel;
    
                    @ApiModelProperty("发布状态[0-未发布，1-已发布]")
    @TableField("publish")
    private Object publish;
    

}