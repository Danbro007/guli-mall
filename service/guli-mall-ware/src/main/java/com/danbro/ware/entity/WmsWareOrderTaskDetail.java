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
@ApiModel("库存工作单")
public class WmsWareOrderTaskDetail implements Serializable {
    private static final long serialVersionUID = -85001193774750349L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("sku_id")
    private Long skuId;

    @ApiModelProperty("sku_name")
    private String skuName;

    @ApiModelProperty("购买个数")
    private Integer skuNum;

    @ApiModelProperty("工作单id")
    private Long taskId;


}