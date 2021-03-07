package com.danbro.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Data
@Accessors(chain = true)
@ApiModel("sku信息")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PmsSkuInfo implements Serializable {
    private static final long serialVersionUID = -79401659768891666L;

    @TableId
    @ApiModelProperty("skuId")
    private Long skuId;

    @ApiModelProperty("spuId")
    private Long spuId;

    @ApiModelProperty("sku名称")
    private String skuName;

    @ApiModelProperty("sku介绍描述")
    private String skuDesc;

    @ApiModelProperty("所属分类id")
    private Long catalogId;

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
}