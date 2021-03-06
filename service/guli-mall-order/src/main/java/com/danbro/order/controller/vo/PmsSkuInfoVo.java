package com.danbro.order.controller.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname PmsSkuInfoVo
 * @Description TODO
 * @Date 2021/2/12 21:31
 * @Created by Administrator
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class PmsSkuInfoVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("skuId")
    private Long skuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("spuId")
    private Long spuId;

    @ApiModelProperty("sku名称")
    private String skuName;

    @ApiModelProperty("sku介绍描述图片")
    private String skuDesc;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属分类id")
    private Long catalogId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("默认图片")
    private String skuDefaultImg;

    @ApiModelProperty("标题")
    private String skuTitle;

    @ApiModelProperty("副标题")
    private String skuSubtitle;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("销量")
    private Long saleCount;

    @ApiModelProperty("销售属性")
    private List<PmsSkuSaleAttrValueVo> attr;

    @ApiModelProperty("图片地址")
    private List<PmsSkuImagesVo> images;

    @ApiModelProperty("sku 选择的品类")
    private List<String> descar;

    @ApiModelProperty("满足打折的数量")
    private Integer fullCount;

    @ApiModelProperty("折扣数")
    private BigDecimal discount;

    @ApiModelProperty("是否可叠加打折的优惠")
    private Boolean countStatus;

    @ApiModelProperty("满足满减的价格")
    private BigDecimal fullPrice;

    @ApiModelProperty("满足满减条件减多少钱")
    private BigDecimal reducePrice;

    @ApiModelProperty("是否可叠加满减的优惠")
    private Boolean priceStatus;

}
