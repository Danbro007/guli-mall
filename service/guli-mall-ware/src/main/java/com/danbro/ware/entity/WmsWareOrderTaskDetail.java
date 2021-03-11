package com.danbro.ware.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Data
@Accessors(chain = true)
@ApiModel("库存工作单")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @ApiModelProperty("订单项锁定状态：1-锁定 2-解锁 3-扣减")
    private Integer lockStatus;

    @ApiModelProperty("库存ID")
    private Long wareId;
}