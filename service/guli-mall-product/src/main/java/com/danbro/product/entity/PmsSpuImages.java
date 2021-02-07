package com.danbro.product.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Data
@Accessors(chain = true)
@ApiModel("spu图片")
public class PmsSpuImages implements Serializable {
    private static final long serialVersionUID = -42799640499637551L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("spu_id")
    private Long spuId;

    @ApiModelProperty("图片名")
    private String imgName;

    @ApiModelProperty("图片地址")
    private String imgUrl;

    @ApiModelProperty("顺序")
    private Integer imgSort;

    @ApiModelProperty("是否默认图")
    private Boolean defaultImg;


}