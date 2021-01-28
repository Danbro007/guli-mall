package com.danbro.member.entity;

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
 * @since 2021-01-28 19:03:18
 */
@Data
@Accessors(chain = true)
@ApiModel("会员登录记录")
public class UmsMemberLoginLog implements Serializable {
    private static final long serialVersionUID = -85907665570844137L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("member_id")
    private Long memberId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Locale createTime;

    @ApiModelProperty("ip")
    private String ip;

    @ApiModelProperty("city")
    private String city;

    @ApiModelProperty("登录类型[1-web，2-app]")
    private Integer loginType;


}