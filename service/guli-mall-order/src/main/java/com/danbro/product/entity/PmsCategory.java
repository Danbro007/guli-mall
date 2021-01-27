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
@ApiModel("商品三级分类")
public class PmsCategory implements Serializable {
    private static final long serialVersionUID = -66145494227699242L;
                    @ApiModelProperty("分类id")
    @TableField("cat_id")
    private Long catId;
    
                    @ApiModelProperty("分类名称")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("父分类id")
    @TableField("parent_cid")
    private Long parentCid;
    
                    @ApiModelProperty("层级")
    @TableField("cat_level")
    private Integer catLevel;
    
                    @ApiModelProperty("是否显示[0-不显示，1显示]")
    @TableField("show_status")
    private Object showStatus;
    
                    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
    
                    @ApiModelProperty("图标地址")
    @TableField("icon")
    private String icon;
    
                    @ApiModelProperty("计量单位")
    @TableField("product_unit")
    private String productUnit;
    
                    @ApiModelProperty("商品数量")
    @TableField("product_count")
    private Integer productCount;
    

}