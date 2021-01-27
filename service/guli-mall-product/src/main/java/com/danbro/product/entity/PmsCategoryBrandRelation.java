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
@ApiModel("品牌分类关联")
public class PmsCategoryBrandRelation implements Serializable {
    private static final long serialVersionUID = -56651172074127330L;
                    @ApiModelProperty("$column.comment")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("品牌id")
    @TableField("brand_id")
    private Long brandId;
    
                    @ApiModelProperty("分类id")
    @TableField("catelog_id")
    private Long catelogId;
    
                    @ApiModelProperty("$column.comment")
    @TableField("brand_name")
    private String brandName;
    
                    @ApiModelProperty("$column.comment")
    @TableField("catelog_name")
    private String catelogName;
    

}