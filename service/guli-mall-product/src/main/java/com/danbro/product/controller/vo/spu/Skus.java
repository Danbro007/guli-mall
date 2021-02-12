/**
 * Copyright 2021 json.cn
 */
package com.danbro.product.controller.vo.spu;

import java.math.BigDecimal;
import java.util.List;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Auto-generated: 2021-02-11 19:7:8
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Skus {

    @ApiModelProperty("销售属性")
    private List<SaleAttr> attr;

    @ApiModelProperty("sku名")
    private String skuName;

    @ApiModelProperty("sku价格")
    private BigDecimal price;

    @ApiModelProperty("标题")
    private String skuTitle;

    @ApiModelProperty("副标题")
    private String skuSubtitle;

    @ApiModelProperty("图片地址")
    private List<Images> images;

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

    @ApiModelProperty("会员价格的信息")
    private List<MemberPrice> memberPrice;

}