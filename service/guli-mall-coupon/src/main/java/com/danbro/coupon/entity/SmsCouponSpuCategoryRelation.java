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
@ApiModel("优惠券分类关联")
public class SmsCouponSpuCategoryRelation implements Serializable {
    private static final long serialVersionUID = 236988254107634744L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("优惠券id")
    private Long couponId;

    @ApiModelProperty("产品分类id")
    private Long categoryId;

    @ApiModelProperty("产品分类名称")
    private String categoryName;


}