package com.danbro.product.controller.vo;

import java.io.Serializable;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsProductAttrValue;
import com.danbro.product.entity.PmsSpuImages;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author liweimo
 * @Classname PmsProductAttrValueVo
 * @Description TODO 商品属性值Vo
 * @Date 2021/2/11 20:51
 * @Created by Administrator
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class PmsProductAttrValueVo implements Serializable, Converter<PmsProductAttrValue, PmsProductAttrValueVo> {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品id")
    private Long spuId;

    @JsonSerialize(using = ToStringSerializer.class)
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

    @Override
    public PmsProductAttrValueVo convertToVo(PmsProductAttrValue pmsProductAttrValue) {
        MyBeanUtils.copyProperties(pmsProductAttrValue, this);
        return this;
    }

    @Override
    public PmsProductAttrValue convertToEntity() {
        PmsProductAttrValue attrValue = new PmsProductAttrValue();
        MyBeanUtils.copyProperties(this, attrValue);
        return attrValue;
    }
}
