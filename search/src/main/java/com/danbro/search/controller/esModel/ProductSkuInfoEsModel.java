package com.danbro.search.controller.esModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@Document(indexName = "product")
public class ProductSkuInfoEsModel implements Serializable {
    @Id
    @ApiModelProperty("skuId")
    private Long skuId;

    @Field(type = FieldType.Keyword)
    @ApiModelProperty("spuId")
    private Long spuId;

    @Field(type = FieldType.Long)
    @ApiModelProperty("所属分类id")
    private Long catalogId;

    @Field(type = FieldType.Keyword)
    @ApiModelProperty("分类名")
    private String catalogName;

    @Field(type = FieldType.Long)
    @ApiModelProperty("品牌id")
    private Long brandId;

    @Field(type = FieldType.Keyword)
    @ApiModelProperty("品牌名")
    private String brandName;

    @Field(type = FieldType.Keyword)
    @ApiModelProperty("品牌logo")
    private String brandImg;

    @Field(type = FieldType.Keyword)
    @ApiModelProperty("默认图片")
    private String skuDefaultImg;

    @Field(type = FieldType.Text, analyzer = "ik_smart")
    @ApiModelProperty("标题")
    private String skuTitle;

    @Field(type = FieldType.Keyword)
    @ApiModelProperty("价格")
    private BigDecimal price;

    @Field(type = FieldType.Long)
    @ApiModelProperty("销量")
    private Long saleCount;

    @Field(type = FieldType.Boolean)
    @ApiModelProperty("是否还有库存")
    private Boolean hasStock;

    @ApiModelProperty("搜索热度")
    @Field(type = FieldType.Long)
    private Long hotScore;

    @ApiModelProperty("sku属性")
    @Field(type = FieldType.Nested)
    private List<ProductAttrEsModel> attrs;


}
