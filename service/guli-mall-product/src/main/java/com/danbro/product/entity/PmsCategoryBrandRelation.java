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
@ApiModel("品牌分类关联")
public class PmsCategoryBrandRelation implements Serializable {
    private static final long serialVersionUID = -54660519928542099L;

    @TableId
    private Long id;

    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("分类id")
    private Long catelogId;

    private String brandName;

    private String catelogName;


}