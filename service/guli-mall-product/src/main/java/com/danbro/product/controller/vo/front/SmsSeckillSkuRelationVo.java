package com.danbro.product.controller.vo.front;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Data
@Accessors(chain = true)
@ApiModel("秒杀活动商品关联")
public class SmsSeckillSkuRelationVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("活动id")
    private Long promotionId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("活动场次id")
    private Long promotionSessionId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("商品id")
    private Long skuId;

    @ApiModelProperty("秒杀价格")
    private BigDecimal seckillPrice;

    @ApiModelProperty("秒杀总量")
    private BigDecimal seckillCount;

    @ApiModelProperty("每人限购数量")
    private BigDecimal seckillLimit;

    @ApiModelProperty("排序")
    private Integer seckillSort;

    /**
     * 秒杀开始时间（时间戳）
     */
    private Long startTime;
    /**
     * 秒杀结束时间（时间戳）
     */
    private Long endTime;
    /**
     * 随机码
     */
    private String randomCode;

}