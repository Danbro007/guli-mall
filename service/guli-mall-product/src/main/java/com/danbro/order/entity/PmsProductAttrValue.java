package com.danbro.order.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Data
@Accessors(chain = true)
@ApiModel("spu属性值")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PmsProductAttrValue implements Serializable {
    private static final long serialVersionUID = 208719867365662179L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("商品id")
    private Long spuId;

    @ApiModelProperty("属性id")
    private Long attrId;

    @ApiModelProperty("属性名")
    private String attrName;

    @ApiModelProperty("属性值")
    private String attrValue;

    @ApiModelProperty("顺序")
    private Integer attrSort;

    @ApiModelProperty("快速展示【是否展示在介绍上；0-否 1-是】")
    private Boolean quickShow;
}