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
@ApiModel("sku销售属性&值")
public class PmsSkuSaleAttrValue implements Serializable {
    private static final long serialVersionUID = 493150895028021588L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("sku_id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("attr_id")
    @TableField("attr_id")
    private Long attrId;
    
                    @ApiModelProperty("销售属性名")
    @TableField("attr_name")
    private String attrName;
    
                    @ApiModelProperty("销售属性值")
    @TableField("attr_value")
    private String attrValue;
    
                    @ApiModelProperty("顺序")
    @TableField("attr_sort")
    private Integer attrSort;
    

}