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
@ApiModel("spu图片")
public class PmsSpuImages implements Serializable {
    private static final long serialVersionUID = -19334588251036851L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("spu_id")
    @TableField("spu_id")
    private Long spuId;
    
                    @ApiModelProperty("图片名")
    @TableField("img_name")
    private String imgName;
    
                    @ApiModelProperty("图片地址")
    @TableField("img_url")
    private String imgUrl;
    
                    @ApiModelProperty("顺序")
    @TableField("img_sort")
    private Integer imgSort;
    
                    @ApiModelProperty("是否默认图")
    @TableField("default_img")
    private Object defaultImg;
    

}