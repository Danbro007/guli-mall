package com.danbro.order.controller.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.order.entity.PmsCategory;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liweimo
 * @Classname PmsCategoryVo
 * @Description TODO
 * @Date 2021/1/28 21:26
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PmsCategoryVo implements Serializable, Converter<PmsCategory, PmsCategoryVo> {
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "修改时分类ID必须存在！", groups = Update.class)
    @Null(message = "添加时分类ID不能存在！", groups = Insert.class)
    @ApiModelProperty("分类id")
    private Long catId;

    @NotBlank(message = "添加时分类名称名必须存在！", groups = Insert.class)
    @ApiModelProperty("分类名称")
    private String name;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("父分类id")
    private Long parentCid;

    @NotNull(message = "添加时层级必须存在！", groups = Insert.class)
    @ApiModelProperty("层级")
    private Integer catLevel;

    @ApiModelProperty("是否显示[0-不显示，1显示]")
    private Boolean showStatus;

    @NotNull(message = "添加时排序必须存在！", groups = Insert.class)
    @ApiModelProperty("排序")
    private Integer sort;

    @NotBlank(message = "添加时图标地址必须存在！", groups = Insert.class)
    @ApiModelProperty("图标地址")
    private String icon;

    @NotBlank(message = "添加时计量单位必须存在！", groups = Insert.class)
    @ApiModelProperty("计量单位")
    private String productUnit;

    @Min(value = 0, message = "商品数量必须大于等于 0！")
    @ApiModelProperty("商品数量")
    private Integer productCount;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ApiModelProperty("子分类")
    private List<PmsCategoryVo> children;

    @Override
    public PmsCategoryVo convertToVo(PmsCategory pmsCategory) {
        MyBeanUtils.copyProperties(pmsCategory, this);
        return this;
    }

    @Override
    public PmsCategory convertToEntity() {
        PmsCategory pmsCategory = new PmsCategory();
        MyBeanUtils.copyProperties(this,pmsCategory);
        return pmsCategory;
    }
}
