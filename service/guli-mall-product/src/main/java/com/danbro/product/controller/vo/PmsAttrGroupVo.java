package com.danbro.product.controller.vo;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsAttr;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Danrbo
 * @Classname PmsAttrGroupVo
 * @Description TODO 属性分组Vo
 * @Date 2021/2/5 22:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PmsAttrGroupVo implements Serializable, Converter<PmsAttrGroup, PmsAttrGroupVo> {
    @NotNull(message = "修改时分组ID必须存在！", groups = Update.class)
    @Null(message = "添加时分组ID不能存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("分组id")
    private Long attrGroupId;

    @NotBlank(message = "添加时组名必须存在！", groups = Insert.class)
    @ApiModelProperty("组名")
    private String attrGroupName;

    @NotNull(message = "添加时排序必须存在！", groups = Insert.class)
    @Min(value = 0, message = "排序必须大于等于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("排序")
    private Integer sort;

    @NotBlank(message = "添加时描述必须存在！", groups = Insert.class)
    @ApiModelProperty("描述")
    private String descript;

    @NotBlank(message = "添加时组图标必须存在！", groups = Insert.class)
    @ApiModelProperty("组图标")
    private String icon;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "添加时所属分类ID必须存在！", groups = Insert.class)
    @ApiModelProperty("所属分类id")
    private Long catelogId;

    @ApiModelProperty("分类路径")
    private String[] catelogPath;

    @ApiModelProperty
    private List<PmsAttrBaseInfoVo> attrs;

    @Override
    public PmsAttrGroupVo convertToVo(PmsAttrGroup pmsAttrGroup) {
        MyBeanUtils.copyProperties(pmsAttrGroup, this);
        return this;
    }

    @Override
    public PmsAttrGroup convertToEntity() {
        PmsAttrGroup pmsAttrGroup = new PmsAttrGroup();
        MyBeanUtils.copyProperties(this,pmsAttrGroup);
        return pmsAttrGroup;
    }

}
