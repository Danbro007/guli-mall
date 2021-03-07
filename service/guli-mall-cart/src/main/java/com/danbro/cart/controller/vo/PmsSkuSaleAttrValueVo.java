package com.danbro.cart.controller.vo;

import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Danrbo
 * @Classname PmsSkuSaleAttrValueVo
 * @Description TODO
 * @Date 2021/3/6 15:54
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class PmsSkuSaleAttrValueVo implements Serializable{
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

}
