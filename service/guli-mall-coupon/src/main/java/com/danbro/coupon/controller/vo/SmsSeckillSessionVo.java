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
import com.danbro.coupon.entity.SmsSeckillSession;
import com.danbro.service.common.validtors.anotations.IsBool;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Data
@Accessors(chain = true)
@ApiModel("秒杀活动场次")
public class SmsSeckillSessionVo implements Serializable, Converter<SmsSeckillSession, SmsSeckillSessionVo> {

    @NotNull(message = "修改时每日秒杀活动ID必须存在！", groups = Update.class)
    @Null(message = "添加时每日秒杀活动ID不能存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @NotBlank(message = "添加每日秒杀活动时场次名称必须存在！", groups = Insert.class)
    @ApiModelProperty("场次名称")
    private String name;

    @NotNull(message = "添加时每日秒杀活动开始时间必须存在！", groups = Insert.class)
    @ApiModelProperty("每日开始时间")
    private Date startTime;

    @NotNull(message = "添加时每日秒杀活动结束时间必须存在！", groups = Insert.class)
    @ApiModelProperty("每日结束时间")
    private Date endTime;

    @IsBool(message = "每日秒杀活动的状态必须开启并且参数正确", groups = {Insert.class, Update.class})
    @ApiModelProperty("启用状态")
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;


    @Override
    public SmsSeckillSessionVo convertToVo(SmsSeckillSession smsSeckillSession) {
        MyBeanUtils.copyProperties(smsSeckillSession, this);
        return this;
    }

    @Override
    public SmsSeckillSession convertToEntity() {
        SmsSeckillSession smsSeckillSession = new SmsSeckillSession();
        MyBeanUtils.copyProperties(this, smsSeckillSession);
        return smsSeckillSession;
    }
}