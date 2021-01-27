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
@ApiModel("品牌")
public class PmsBrand implements Serializable {
    private static final long serialVersionUID = -83807163731679847L;
                    @ApiModelProperty("品牌id")
    @TableField("brand_id")
    private Long brandId;
    
                    @ApiModelProperty("品牌名")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("品牌logo地址")
    @TableField("logo")
    private String logo;
    
                    @ApiModelProperty("介绍")
    @TableField("descript")
    private Object descript;
    
                    @ApiModelProperty("显示状态[0-不显示；1-显示]")
    @TableField("show_status")
    private Object showStatus;
    
                    @ApiModelProperty("检索首字母")
    @TableField("first_letter")
    private String firstLetter;
    
                    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
    

}