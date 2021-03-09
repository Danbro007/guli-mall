package com.danbro.product.controller.vo;

import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsCategoryBrandRelation;
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * @Classname PmsCategoryBrandRelationVo
 * @Description TODO
 * @Date 2021/2/7 15:01
 * @Created by Administrator
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class PmsCategoryBrandRelationVo implements Serializable, Converter<PmsCategoryBrandRelation, PmsCategoryBrandRelationVo> {
    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "修改时品牌分类ID必须存在！", groups = Update.class)
    @Null(message = "添加时品牌分类ID不能存在！", groups = Insert.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "添加时品牌ID必须存在！", groups = Insert.class)
    @NotNull(message = "修改时品牌ID必须存在！", groups = Update.class)
    @ApiModelProperty("品牌id")
    private Long brandId;

    @JsonSerialize(using = ToStringSerializer.class)
    @NotNull(message = "添加时分类ID必须存在！", groups = Insert.class)
    @NotNull(message = "修改时分类ID必须存在！", groups = Update.class)
    @ApiModelProperty("分类id")
    private Long catelogId;

    private String brandName;

    private String catelogName;

    @Override
    public PmsCategoryBrandRelationVo convertToVo(PmsCategoryBrandRelation pmsCategoryBrandRelation) {
        MyBeanUtils.copyProperties(pmsCategoryBrandRelation, this);
        return this;
    }

    @Override
    public PmsCategoryBrandRelation convertToEntity() {
        PmsCategoryBrandRelation pmsCategoryBrandRelation = new PmsCategoryBrandRelation();
        MyBeanUtils.copyProperties(this,pmsCategoryBrandRelation);
        return pmsCategoryBrandRelation;
    }
}
