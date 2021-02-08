package com.danbro.product.controller.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsBrand;
import com.danbro.common.interfaces.Converter;
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
import org.hibernate.validator.constraints.URL;

/**
 * @Classname PmsBrandVo
 * @Description TODO 品牌Vo
 * @Date 2021/2/2 22:28
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PmsBrandVo implements Converter<PmsBrand, PmsBrandVo> {

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "修改时品牌ID必须存在！", groups = Update.class)
    @Null(message = "添加时品牌ID不能存在！", groups = Insert.class)
    @ApiModelProperty("品牌id")
    private Long brandId;

    @NotBlank(message = "添加时品牌名必须存在！", groups = Insert.class)
    @ApiModelProperty("品牌名")
    private String name;

    @NotBlank(message = "添加时 Logo 地址必须存在！", groups = Insert.class)
    @URL(message = "品牌 logo 地址格式不符合！", groups = {Insert.class, Update.class})
    @ApiModelProperty("品牌logo地址")
    private String logo;

    @NotBlank(message = "添加时品牌介绍必须存在！", groups = Insert.class)
    @ApiModelProperty("介绍")
    private String descript;

    @NotNull(message = "添加时显示状态必须存在！", groups = Insert.class)
    @IsBool(groups = {Insert.class, Update.class})
    @ApiModelProperty("显示状态[0-不显示；1-显示]")
    private Boolean showStatus;

    @NotBlank(message = "添加时检索首字母必须存在！", groups = Insert.class)
    @Pattern(regexp = "^[A-Za-z]$", message = "检索首字母必须是一个英文字母！", groups = {Insert.class, Update.class})
    @ApiModelProperty("检索首字母")
    private String firstLetter;

    @NotNull(message = "添加时排序必须存在！", groups = Insert.class)
    @Min(value = 0, message = "排序必须大于等于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("排序")
    private Integer sort;


    @Override
    public PmsBrandVo convertToVo(PmsBrand pmsBrand) {
        MyBeanUtils.copyProperties(pmsBrand, this);
        return this;
    }

    @Override
    public PmsBrand convertToEntity() {
        PmsBrand pmsBrand = new PmsBrand();
        MyBeanUtils.copyProperties(this, pmsBrand);
        return pmsBrand;
    }
}
