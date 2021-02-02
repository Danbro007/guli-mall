package com.danbro.ware.entity;

import java.math.BigDecimal;
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
 * @since 2021-01-28 19:06:15
 */
@Data
@Accessors(chain = true)
@ApiModel("采购信息")
public class WmsPurchase implements Serializable {
    private static final long serialVersionUID = -58208221687629002L;

    @TableId
    @ApiModelProperty("采购单id")
    private Long id;

    @ApiModelProperty("采购人id")
    private Long assigneeId;

    @ApiModelProperty("采购人名")
    private String assigneeName;

    @ApiModelProperty("联系方式")
    private String phone;

    @ApiModelProperty("优先级")
    private Integer priority;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("仓库id")
    private Long wareId;

    @ApiModelProperty("总金额")
    private BigDecimal amount;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建日期")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新日期")
    private Date updateTime;


}