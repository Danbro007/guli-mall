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
@ApiModel("成长值变化历史记录")
public class UmsGrowthChangeHistory implements Serializable {
    private static final long serialVersionUID = 679725540803830633L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("member_id")
    private Long memberId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("create_time")
    private Locale createTime;

    @ApiModelProperty("改变的值（正负计数）")
    private Integer changeCount;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("积分来源[0-购物，1-管理员修改]")
    private Integer sourceType;


}