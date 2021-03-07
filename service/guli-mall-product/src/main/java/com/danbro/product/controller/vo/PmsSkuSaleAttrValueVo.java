package com.danbro.product.controller.vo;

import java.io.Serializable;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsSkuSaleAttrValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname PmsSkuSaleAttrValueVo
 * @Description TODO
 * @Date 2021/2/11 21:45
 * @Created by Administrator
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class PmsSkuSaleAttrValueVo implements Serializable, Converter<PmsSkuSaleAttrValue, PmsSkuSaleAttrValueVo> {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("属性id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("sku_id")
    private Long skuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("attr_id")
    private Long attrId;

    @ApiModelProperty("销售属性名")
    private String attrName;

    @ApiModelProperty("销售属性值")
    private String attrValue;

    @ApiModelProperty("顺序")
    private Integer attrSort;

    @Override
    public PmsSkuSaleAttrValueVo convertToVo(PmsSkuSaleAttrValue pmsSkuSaleAttrValue) {
        MyBeanUtils.copyProperties(pmsSkuSaleAttrValue, this);
        return this;
    }

    @Override
    public PmsSkuSaleAttrValue convertToEntity() {
        PmsSkuSaleAttrValue pmsSkuSaleAttrValue = new PmsSkuSaleAttrValue();
        MyBeanUtils.copyProperties(this, pmsSkuSaleAttrValue);
        return pmsSkuSaleAttrValue;

    }
}
