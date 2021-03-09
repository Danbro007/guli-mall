package com.danbro.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Data
@Accessors(chain = true)
@ApiModel("sku图片")
public class PmsSkuImages implements Serializable {
    private static final long serialVersionUID = 328965027816131522L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("sku_id")
    private Long skuId;

    @ApiModelProperty("图片地址")
    private String imgUrl;

    @ApiModelProperty("排序")
    private Integer imgSort;

    @ApiModelProperty("默认图[0 - 不是默认图，1 - 是默认图]")
    private Boolean defaultImg;


}