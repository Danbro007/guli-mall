package com.danbro.coupon.controller.vo;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.coupon.entity.SmsSeckillPromotion;
import com.danbro.service.common.validtors.anotations.IsBool;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SmsSeckillPromotionVo
 * @Description TODO
 * @Date 2021/3/15 21:40
 * @Created by Administrator
 */
@Data
public class SmsSeckillPromotionVo implements Serializable, Converter<SmsSeckillPromotion, SmsSeckillPromotionVo> {

    @NotNull(message = "修改时秒杀活动ID必须存在！", groups = Update.class)
    @Null(message = "添加时秒杀ID不能存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @NotBlank(message = "添加时秒杀活动标题必须存在！", groups = Insert.class)
    @ApiModelProperty("活动标题")
    private String title;

    @NotNull(message = "添加时秒杀活动开始时间必须存在！", groups = Insert.class)
    @ApiModelProperty("开始日期")
    private Date startTime;

    @NotNull(message = "添加时秒杀活动结束时间必须存在！", groups = Insert.class)
    @ApiModelProperty("结束日期")
    private Date endTime;

    @IsBool(message = "添加时秒杀上下线状态必须存在！", groups = Insert.class)
    @ApiModelProperty("上下线状态")
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("创建人")
    private Long userId;

    @Override
    public SmsSeckillPromotionVo convertToVo(SmsSeckillPromotion smsSeckillPromotion) {
        MyBeanUtils.copyProperties(smsSeckillPromotion, this);
        return this;
    }

    @Override
    public SmsSeckillPromotion convertToEntity() {
        SmsSeckillPromotion smsSeckillPromotion = new SmsSeckillPromotion();
        MyBeanUtils.copyProperties(this, smsSeckillPromotion);
        return smsSeckillPromotion;
    }
}
