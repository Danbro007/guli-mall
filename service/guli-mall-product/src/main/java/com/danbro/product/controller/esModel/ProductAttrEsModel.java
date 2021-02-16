package com.danbro.product.controller.esModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Classname PmsAttr
 * @Description TODO elasticsearch的sku商品属性数据模型对象
 * @Date 2021/2/15 21:29
 * @Created by Administrator
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class ProductAttrEsModel implements Serializable {
    @ApiModelProperty("属性ID")
    private Long attrId;

    @ApiModelProperty("属性名")
    private String attrName;

    @ApiModelProperty("属性值")
    private String attrValue;
}
