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
@ApiModel("spu信息介绍")
public class PmsSpuInfoDesc implements Serializable {
    private static final long serialVersionUID = 846713687630915026L;

    @TableId
    @ApiModelProperty("商品id")
    private Long spuId;

    @ApiModelProperty("商品介绍")
    private Object decript;


}