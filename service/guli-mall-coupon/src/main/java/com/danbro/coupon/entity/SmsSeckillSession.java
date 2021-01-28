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
@ApiModel("秒杀活动场次")
public class SmsSeckillSession implements Serializable {
    private static final long serialVersionUID = -34233813191261338L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("场次名称")
    private String name;

    @ApiModelProperty("每日开始时间")
    private Locale startTime;

    @ApiModelProperty("每日结束时间")
    private Locale endTime;

    @ApiModelProperty("启用状态")
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Locale createTime;


}