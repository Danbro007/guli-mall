package com.danbro.coupon.entity;

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
@ApiModel("优惠券与产品关联")
public class SmsCouponSpuRelation implements Serializable {
    private static final long serialVersionUID = -55999226245591096L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("优惠券id")
    private Long couponId;

    @ApiModelProperty("spu_id")
    private Long spuId;

    @ApiModelProperty("spu_name")
    private String spuName;


}