package com.danbro.coupon.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.coupon.entity.SmsSkuLadder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname SmsSkuLadderVo
 * @Description TODO
 * @Date 2021/2/12 17:10
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Accessors(chain = true)
public class SmsSkuLadderVo implements Serializable, Converter<SmsSkuLadder, SmsSkuLadderVo> {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("spu_id")
    private Long skuId;

    @ApiModelProperty("满几件")
    private Integer fullCount;

    @ApiModelProperty("折扣数")
    private BigDecimal discount;

    @ApiModelProperty("折后价")
    private BigDecimal price;

    @ApiModelProperty("是否叠加其他优惠[0-不可叠加，1-可叠加]")
    private Boolean addOther;

    @Override
    public SmsSkuLadderVo convertToVo(SmsSkuLadder smsSkuLadder) {
        MyBeanUtils.copyProperties(smsSkuLadder, this);
        return this;
    }

    @Override
    public SmsSkuLadder convertToEntity() {
        SmsSkuLadder smsSkuLadder = new SmsSkuLadder();
        MyBeanUtils.copyProperties(this, smsSkuLadder);
        return smsSkuLadder;
    }
}
