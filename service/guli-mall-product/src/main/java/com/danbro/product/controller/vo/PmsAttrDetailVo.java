package com.danbro.product.controller.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsAttr;
import com.danbro.service.common.validtors.anotations.IsBool;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname PmsAttrVo
 * @Description TODO 属性对象的详细信息
 * @Date 2021/2/8 12:40
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class PmsAttrDetailVo implements Converter<PmsAttr, PmsAttrDetailVo> {
    @NotNull(message = "修改时属性ID必须存在！", groups = Update.class)
    @Null(message = "添加时属性ID不能存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("属性id")
    private Long attrId;

    @NotEmpty(message = "添加时属性名必须存在！", groups = Insert.class)
    @ApiModelProperty("属性名")
    private String attrName;

    @NotNull(message = "添加时是否检索必须存在", groups = Insert.class)
    @IsBool(groups = {Insert.class, Update.class})
    @ApiModelProperty("是否需要检索[0-不需要，1-需要]")
    private Boolean searchType;

    @NotBlank(message = "添加时属性图标必须存在！", groups = Insert.class)
    @ApiModelProperty("属性图标")
    private String icon;

    @ApiModelProperty("可选值列表[用逗号分隔]")
    private String valueSelect;

    @NotNull(message = "添加时属性类型必须存在！", groups = Insert.class)
    @ApiModelProperty("属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Integer attrType;

    @NotNull(message = "添加时启用状态必须存在", groups = Insert.class)
    @IsBool(groups = {Insert.class, Update.class})
    @ApiModelProperty("启用状态[0 - 禁用，1 - 启用]")
    private Boolean enable;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "添加时所属分类ID必须存在", groups = Insert.class)
    @ApiModelProperty("所属分类")
    private Long catelogId;

    @NotNull(message = "添加时快速展示必须存在", groups = Insert.class)
    @IsBool(groups = {Insert.class, Update.class})
    @ApiModelProperty("快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    private Boolean showDesc;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("属性分组ID")
    private Long attrGroupId;

    @ApiModelProperty("可选值模式")
    private Integer valueType;

    @ApiModelProperty("分类路径")
    private String[] catelogPath;


    @Override
    public PmsAttrDetailVo convertToVo(PmsAttr pmsAttr) {
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
