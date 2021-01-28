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
@ApiModel("秒杀活动")
public class SmsSeckillPromotion implements Serializable {
    private static final long serialVersionUID = -29954478491778688L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("活动标题")
    private String title;

    @ApiModelProperty("开始日期")
    private Locale startTime;

    @ApiModelProperty("结束日期")
    private Locale endTime;

    @ApiModelProperty("上下线状态")
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Locale createTime;

    @ApiModelProperty("创建人")
    private Long userId;


}