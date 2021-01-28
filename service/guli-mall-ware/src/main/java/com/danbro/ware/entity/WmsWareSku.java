package com.danbro.ware.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Data
@Accessors(chain = true)
@ApiModel("商品库存")
public class WmsWareSku implements Serializable {
    private static final long serialVersionUID = 993502285628939658L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("sku_id")
    private Long skuId;

    @ApiModelProperty("仓库id")
    private Long wareId;

    @ApiModelProperty("库存数")
    private Integer stock;

    @ApiModelProperty("sku_name")
    private String skuName;

    @ApiModelProperty("锁定库存")
    private Integer stockLocked;


}