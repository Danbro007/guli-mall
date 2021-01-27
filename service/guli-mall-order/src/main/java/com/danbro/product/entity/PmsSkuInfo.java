package com.danbro.product.entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Data
@Accessors(chain = true)
@ApiModel("sku信息")
public class PmsSkuInfo implements Serializable {
    private static final long serialVersionUID = 775894319286685400L;
                    @ApiModelProperty("skuId")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("spuId")
    @TableField("spu_id")
    private Long spuId;
    
                    @ApiModelProperty("sku名称")
    @TableField("sku_name")
    private String skuName;
    
                    @ApiModelProperty("sku介绍描述")
    @TableField("sku_desc")
    private String skuDesc;
    
                    @ApiModelProperty("所属分类id")
    @TableField("catalog_id")
    private Long catalogId;
    
                    @ApiModelProperty("品牌id")
    @TableField("brand_id")
    private Long brandId;
    
                    @ApiModelProperty("默认图片")
    @TableField("sku_default_img")
    private String skuDefaultImg;
    
                    @ApiModelProperty("标题")
    @TableField("sku_title")
    private String skuTitle;
    
                    @ApiModelProperty("副标题")
    @TableField("sku_subtitle")
    private String skuSubtitle;
    
                    @ApiModelProperty("价格")
    @TableField("price")
    private Double price;
    
                    @ApiModelProperty("销量")
    @TableField("sale_count")
    private Long saleCount;
    

}