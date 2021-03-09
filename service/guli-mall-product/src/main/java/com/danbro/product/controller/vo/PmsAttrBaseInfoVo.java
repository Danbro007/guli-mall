package com.danbro.product.controller.vo;

import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsAttr;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname PmsAttrBaseInfoVo
 * @Description TODO 属性对象的基本信息
 * @Date 2021/2/8 19:23
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PmsAttrBaseInfoVo implements Converter<PmsAttr, PmsAttrBaseInfoVo> {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("属性id")
    private Long attrId;

    @ApiModelProperty("属性名")
    private String attrName;

    @ApiModelProperty("是否需要检索[0-不需要，1-需要]")
    private Boolean searchType;

    @ApiModelProperty("可选值模式")
    private Integer valueType;

    @ApiModelProperty("属性图标")
    private String icon;

    @ApiModelProperty("可选值列表[用逗号分隔]")
    private String valueSelect;

    @ApiModelProperty("启用状态[0 - 禁用，1 - 启用]")
    private Boolean enable;

    @ApiModelProperty("快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    private Boolean showDesc;

    @ApiModelProperty("所属分组名")
    private String groupName;

    @ApiModelProperty("所属分类名")
    private String catelogName;

    @Override
    public PmsAttrBaseInfoVo convertToVo(PmsAttr pmsAttr) {
        MyBeanUtils.copyProperties(pmsAttr, this);
        return this;
    }

    @Override
    public PmsAttr convertToEntity() {
        PmsAttr pmsAttr = new PmsAttr();
        MyBeanUtils.copyProperties(this, pmsAttr);
        return pmsAttr;
    }
}
