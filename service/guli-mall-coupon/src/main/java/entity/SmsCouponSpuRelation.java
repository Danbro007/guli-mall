package entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Data
@Accessors(chain = true)
@ApiModel("优惠券与产品关联")
public class SmsCouponSpuRelation implements Serializable {
    private static final long serialVersionUID = -73708002376523907L;
    @ApiModelProperty("id")
    @TableField("id")
    private Long id;

    @ApiModelProperty("优惠券id")
    @TableField("coupon_id")
    private Long couponId;

    @ApiModelProperty("spu_id")
    @TableField("spu_id")
    private Long spuId;

    @ApiModelProperty("spu_name")
    @TableField("spu_name")
    private String spuName;


}