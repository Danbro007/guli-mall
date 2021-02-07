package com.danbro.product.controller.vo;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.ConvertToVo;
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

/**
 * @Classname PmsCategoryBrandRelationVo
 * @Description TODO
 * @Date 2021/2/7 15:01
 * @Created by Administrator
 */
@Data
@Builder
@AllArgsConstructor
public class PmsCategoryBrandRelationVo implements Serializable, ConvertToVo<PmsCategoryBrandRelation, PmsCategoryBrandRelationVo> {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("品牌id")
    private Long brandId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("分类id")
    private Long catelogId;

    private String brandName;

    private String catelogName;

    @Override
    public PmsCategoryBrandRelationVo convert(PmsCategoryBrandRelation pmsCategoryBrandRelation) {
        MyBeanUtils.copyProperties(pmsCategoryBrandRelation, this);
        return this;
    }
}
