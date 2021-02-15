package com.danbro.product.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname SmsMemberPriceVo
 * @Description TODO
 * @Date 2021/2/11 22:24
 * @Created by Administrator
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class SmsMemberPriceVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("sku_id")
    private Long skuId;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonAlias("id")
    @ApiModelProperty("会员等级id")
    private Long memberLevelId;

    @JsonAlias("name")
    @ApiModelProperty("会员等级名")
    private String memberLevelName;

    @JsonAlias("price")
    @ApiModelProperty("会员对应价格")
    private BigDecimal memberPrice;

    @ApiModelProperty("可否叠加其他优惠[0-不可叠加优惠，1-可叠加]")
    private Boolean addOther;

}
