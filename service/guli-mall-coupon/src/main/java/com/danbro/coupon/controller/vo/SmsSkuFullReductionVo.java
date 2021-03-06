package com.danbro.coupon.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.coupon.entity.SmsSkuFullReduction;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class SmsSkuFullReductionVo implements Serializable, Converter<SmsSkuFullReduction, SmsSkuFullReductionVo> {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("spu_id")
    private Long skuId;

    @ApiModelProperty("满多少")
    private BigDecimal fullPrice;

    @ApiModelProperty("减多少")
    private BigDecimal reducePrice;

    @ApiModelProperty("是否参与其他优惠")
    private Boolean addOther;

    @Override
    public SmsSkuFullReductionVo convertToVo(SmsSkuFullReduction smsSkuFullReduction) {
        MyBeanUtils.copyProperties(smsSkuFullReduction, this);
        return this;
    }

    @Override
    public SmsSkuFullReduction convertToEntity() {
        SmsSkuFullReduction smsSkuFullReduction = new SmsSkuFullReduction();
        MyBeanUtils.copyProperties(this, smsSkuFullReduction);
        return smsSkuFullReduction;
    }
}
