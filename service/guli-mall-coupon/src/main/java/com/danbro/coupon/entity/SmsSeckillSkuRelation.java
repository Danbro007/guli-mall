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
@ApiModel("秒杀活动商品关联")
public class SmsSeckillSkuRelation implements Serializable {
    private static final long serialVersionUID = 325160222964543220L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("活动id")
    private Long promotionId;

    @ApiModelProperty("活动场次id")
    private Long promotionSessionId;

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


}