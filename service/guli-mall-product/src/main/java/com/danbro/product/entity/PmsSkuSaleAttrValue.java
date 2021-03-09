package com.danbro.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Data
@Accessors(chain = true)
@ApiModel("sku销售属性&值")
public class PmsSkuSaleAttrValue implements Serializable {
    private static final long serialVersionUID = -59323441062194843L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("sku_id")
    private Long skuId;

    @ApiModelProperty("attr_id")
    private Long attrId;

    @ApiModelProperty("销售属性名")
    private String attrName;

    @ApiModelProperty("销售属性值")
    private String attrValue;

    @ApiModelProperty("顺序")
    private Integer attrSort;

}