package com.danbro.coupon.controller.vo;

import java.math.BigDecimal;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.coupon.entity.SmsMemberPrice;
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
public class SmsMemberPriceVo implements Converter<SmsMemberPrice, SmsMemberPriceVo> {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("sku_id")
    private Long skuId;

    @ApiModelProperty("会员等级id")
    private Long memberLevelId;

    @ApiModelProperty("会员等级名")
    private String memberLevelName;

    @ApiModelProperty("会员对应价格")
    private BigDecimal memberPrice;

    @ApiModelProperty("可否叠加其他优惠[0-不可叠加优惠，1-可叠加]")
    private Boolean addOther;

    @Override
    public SmsMemberPriceVo convertToVo(SmsMemberPrice smsMemberPrice) {
        MyBeanUtils.copyProperties(smsMemberPrice, this);
        return this;
    }

    @Override
    public SmsMemberPrice convertToEntity() {
        SmsMemberPrice smsMemberPrice = new SmsMemberPrice();
        MyBeanUtils.copyProperties(this, smsMemberPrice);
        return smsMemberPrice;
    }
}
