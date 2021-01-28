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
@ApiModel("商品会员价格")
public class SmsMemberPrice implements Serializable {
    private static final long serialVersionUID = -43843579797529904L;

    @TableId
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


}