package com.danbro.coupon.controller.vo;

import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.coupon.entity.SmsSeckillSkuRelation;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Data
@Accessors(chain = true)
@ApiModel("秒杀活动商品关联")
public class SmsSeckillSkuRelationVo implements Serializable, Converter<SmsSeckillSkuRelation, SmsSeckillSkuRelationVo> {
    @NotNull(message = "修改时秒杀活动商品关联ID必须存在！", groups = Update.class)
    @Null(message = "添加时秒杀活动商品关联ID不能存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @NotNull(message = "添加时秒杀活动ID必须存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("活动id")
    private Long promotionId;


    @NotNull(message = "添加时秒杀场次ID必须存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("活动场次id")
    private Long promotionSessionId;

    @NotNull(message = "添加时商品ID必须存在！", groups = Insert.class)
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


    @Override
    public SmsSeckillSkuRelationVo convertToVo(SmsSeckillSkuRelation smsSeckillSkuRelation) {
        MyBeanUtils.copyProperties(smsSeckillSkuRelation, this);
        return this;
    }

    @Override
    public SmsSeckillSkuRelation convertToEntity() {
        SmsSeckillSkuRelation smsSeckillSkuRelation = new SmsSeckillSkuRelation();
        MyBeanUtils.copyProperties(this, smsSeckillSkuRelation);
        return smsSeckillSkuRelation;
    }
}