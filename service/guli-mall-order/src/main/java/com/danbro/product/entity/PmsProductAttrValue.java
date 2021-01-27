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
@ApiModel("spu属性值")
public class PmsProductAttrValue implements Serializable {
    private static final long serialVersionUID = 295966358867700620L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("商品id")
    @TableField("spu_id")
    private Long spuId;
    
                    @ApiModelProperty("属性id")
    @TableField("attr_id")
    private Long attrId;
    
                    @ApiModelProperty("属性名")
    @TableField("attr_name")
    private String attrName;
    
                    @ApiModelProperty("属性值")
    @TableField("attr_value")
    private String attrValue;
    
                    @ApiModelProperty("顺序")
    @TableField("attr_sort")
    private Integer attrSort;
    
                    @ApiModelProperty("快速展示【是否展示在介绍上；0-否 1-是】")
    @TableField("quick_show")
    private Object quickShow;
    

}