package com.danbro.coupon.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Data
@Accessors(chain = true)
@ApiModel("商品满减信息")
public class SmsSkuFullReduction implements Serializable {
    private static final long serialVersionUID = -17280271851083034L;

    @TableId
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


}