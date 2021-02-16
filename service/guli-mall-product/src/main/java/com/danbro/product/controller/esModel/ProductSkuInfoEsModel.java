package com.danbro.product.controller.esModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author liweimo
 * @Classname PmsSkuInfoVo
 * @Description TODO elasticsearch的sku商品数据模型对象
 * @Date 2021/2/15 21:20
 * @Created by Administrator
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class ProductSkuInfoEsModel implements Serializable {
    @ApiModelProperty("skuId")
    private Long skuId;

    @ApiModelProperty("spuId")
    private Long spuId;

    @ApiModelProperty("所属分类id")
    private Long catalogId;

    @ApiModelProperty("分类名")
    private String catalogName;

    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("品牌名")
    private String brandName;

    @ApiModelProperty("品牌logo")
    private String brandImg;

    @ApiModelProperty("默认图片")
    private String skuDefaultImg;

    @ApiModelProperty("标题")
    private String skuTitle;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("销量")
    private Long saleCount;

    @ApiModelProperty("是否还有库存")
    private Boolean hasStock;

    @ApiModelProperty("搜索热度")
    private Long hotScore;

    @ApiModelProperty("sku属性")
    private List<ProductAttrEsModel> attrs;
}
