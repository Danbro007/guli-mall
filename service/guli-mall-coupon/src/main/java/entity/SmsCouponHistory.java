package entity;

import java.util.Date;
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
@ApiModel("优惠券领取历史记录")
public class SmsCouponHistory implements Serializable {
    private static final long serialVersionUID = 604242391378956579L;
    @ApiModelProperty("id")
    @TableField("id")
    private Long id;

    @ApiModelProperty("优惠券id")
    @TableField("coupon_id")
    private Long couponId;

    @ApiModelProperty("会员id")
    @TableField("member_id")
    private Long memberId;

    @ApiModelProperty("会员名字")
    @TableField("member_nick_name")
    private String memberNickName;

    @ApiModelProperty("获取方式[0->后台赠送；1->主动领取]")
    @TableField("get_type")
    private Object getType;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty("使用状态[0->未使用；1->已使用；2->已过期]")
    @TableField("use_type")
    private Object useType;

    @ApiModelProperty("使用时间")
    @TableField("use_time")
    private Date useTime;

    @ApiModelProperty("订单id")
    @TableField("order_id")
    private Long orderId;

    @ApiModelProperty("订单号")
    @TableField("order_sn")
    private Long orderSn;


}