package com.danbro.product.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname SmsSkuLadderVo
 * @Description TODO
 * @Date 2021/2/12 17:05
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class SmsSkuLadderVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("spu_id")
    private Long skuId;

    @ApiModelProperty("满足打折的商品件数")
    private Integer fullCount;

    @ApiModelProperty("折扣数")
    private BigDecimal discount;

    @ApiModelProperty("折后价")
    private BigDecimal price;

    @ApiModelProperty("是否叠加其他优惠[0-不可叠加，1-可叠加]")
    private Boolean addOther;
}
