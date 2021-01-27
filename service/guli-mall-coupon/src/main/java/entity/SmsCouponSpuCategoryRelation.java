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
@ApiModel("优惠券分类关联")
public class SmsCouponSpuCategoryRelation implements Serializable {
    private static final long serialVersionUID = 663040846091518964L;
    @ApiModelProperty("id")
    @TableField("id")
    private Long id;

    @ApiModelProperty("优惠券id")
    @TableField("coupon_id")
    private Long couponId;

    @ApiModelProperty("产品分类id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty("产品分类名称")
    @TableField("category_name")
    private String categoryName;


}