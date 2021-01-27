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
@ApiModel("sku图片")
public class PmsSkuImages implements Serializable {
    private static final long serialVersionUID = 595801747880406810L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("sku_id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("图片地址")
    @TableField("img_url")
    private String imgUrl;
    
                    @ApiModelProperty("排序")
    @TableField("img_sort")
    private Integer imgSort;
    
                    @ApiModelProperty("默认图[0 - 不是默认图，1 - 是默认图]")
    @TableField("default_img")
    private Integer defaultImg;
    

}