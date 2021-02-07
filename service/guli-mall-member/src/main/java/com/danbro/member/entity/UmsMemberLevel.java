package com.danbro.member.entity;

import java.math.BigDecimal;
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
@ApiModel("会员等级")
public class UmsMemberLevel implements Serializable {
    private static final long serialVersionUID = -99893733480595918L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("等级名称")
    private String name;

    @ApiModelProperty("等级需要的成长值")
    private Integer growthPoint;

    @ApiModelProperty("是否为默认等级[0->不是；1->是]")
    private Boolean defaultStatus;

    @ApiModelProperty("免运费标准")
    private BigDecimal freeFreightPoint;

    @ApiModelProperty("每次评价获取的成长值")
    private Integer commentGrowthPoint;

    @ApiModelProperty("是否有免邮特权")
    private Boolean priviledgeFreeFreight;

    @ApiModelProperty("是否有会员价格特权")
    private Boolean priviledgeMemberPrice;

    @ApiModelProperty("是否有生日特权")
    private Boolean priviledgeBirthday;

    @ApiModelProperty("备注")
    private String note;


}