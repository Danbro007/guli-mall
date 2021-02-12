/**
 * Copyright 2021 json.cn
 */
package com.danbro.product.controller.vo.spu;

import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Auto-generated: 2021-02-11 19:7:8
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class Bounds {

    @ApiModelProperty("购物积分")
    private BigDecimal buyBounds;

    @ApiModelProperty("成长积分")
    private BigDecimal growBounds;
}