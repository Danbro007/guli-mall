package com.danbro.order.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Data
@Accessors(chain = true)
@ApiModel("商品属性")
public class PmsAttr implements Serializable {
    private static final long serialVersionUID = 644321496396538548L;

    @TableId
    @ApiModelProperty("属性id")
    private Long attrId;

    @ApiModelProperty("属性名")
    private String attrName;

    @ApiModelProperty("是否需要检索[0-不需要，1-需要]")
    private Boolean searchType;

    @ApiModelProperty("属性图标")
    private String icon;

    @ApiModelProperty("可选值列表[用逗号分隔]")
    private String valueSelect;

    @ApiModelProperty("属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Integer attrType;

    @ApiModelProperty("启用状态[0 - 禁用，1 - 启用]")
    private Boolean enable;

    @ApiModelProperty("所属分类")
    private Long catelogId;

    @ApiModelProperty("属性是否有多个值，1：是 0：否")
    private Integer valueType;

    @ApiModelProperty("快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    private Boolean showDesc;
}