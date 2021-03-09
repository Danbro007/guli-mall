package com.danbro.product.controller.vo;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Classname SmsSkuFullReductionVo
 * @Description TODO
 * @Date 2021/2/12 17:30
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Accessors(chain = true)
public class SmsSkuFullReductionVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("spu_id")
    private Long skuId;

    @ApiModelProperty("满多少")
    private BigDecimal fullPrice;

    @ApiModelProperty("减多少")
    private BigDecimal reducePrice;

    @Alias("priceStatus")
    @ApiModelProperty("是否参与其他优惠")
    private Boolean addOther;

}
