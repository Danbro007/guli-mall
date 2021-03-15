package com.danbro.order.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Danrbo
 * @Classname WmsWareOrderTaskDetailVo
 * @Description TODO
 * @Date 2021/3/15 10:23
 */
@Data
public class WmsWareOrderTaskDetailVo implements Serializable {
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
