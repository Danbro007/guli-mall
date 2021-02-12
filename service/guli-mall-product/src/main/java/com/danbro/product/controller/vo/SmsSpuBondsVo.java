package com.danbro.product.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname SmsSpuBondsVo
 * @Description TODO
 * @Date 2021/2/12 16:39
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SmsSpuBondsVo implements Serializable {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("spuId")
    private Long spuId;

    @ApiModelProperty("成长积分")
    private BigDecimal growBounds;

    @ApiModelProperty("购物积分")
    private BigDecimal buyBounds;

    @ApiModelProperty("优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]")
    private Integer work;
}
