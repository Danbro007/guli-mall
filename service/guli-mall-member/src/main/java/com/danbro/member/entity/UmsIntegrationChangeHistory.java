package com.danbro.member.entity;

import java.util.Date;
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
@ApiModel("积分变化历史记录")
public class UmsIntegrationChangeHistory implements Serializable {
    private static final long serialVersionUID = 154804217799934570L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("member_id")
    private Long memberId;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("create_time")
    private Date createTime;

    @ApiModelProperty("变化的值")
    private Integer changeCount;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("来源[0->购物；1->管理员修改;2->活动]")
    private Integer sourceTyoe;


}