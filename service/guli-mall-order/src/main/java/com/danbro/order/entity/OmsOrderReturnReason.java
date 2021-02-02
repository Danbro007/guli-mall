package com.danbro.order.entity;

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
 * @since 2021-01-28 18:50:27
 */
@Data
@Accessors(chain = true)
@ApiModel("退货原因")
public class OmsOrderReturnReason implements Serializable {
    private static final long serialVersionUID = -34506159611184127L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("退货原因名")
    private String name;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("启用状态")
    private Boolean status;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("create_time")
    private Date createTime;


}