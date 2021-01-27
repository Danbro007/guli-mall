package com.danbro.member.entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:30:58
 */
@Data
@Accessors(chain = true)
@ApiModel("会员统计信息")
public class UmsMemberStatisticsInfo implements Serializable {
    private static final long serialVersionUID = -76526354760518886L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("会员id")
    @TableField("member_id")
    private Long memberId;
    
                    @ApiModelProperty("累计消费金额")
    @TableField("consume_amount")
    private Double consumeAmount;
    
                    @ApiModelProperty("累计优惠金额")
    @TableField("coupon_amount")
    private Double couponAmount;
    
                    @ApiModelProperty("订单数量")
    @TableField("order_count")
    private Integer orderCount;
    
                    @ApiModelProperty("优惠券数量")
    @TableField("coupon_count")
    private Integer couponCount;
    
                    @ApiModelProperty("评价数")
    @TableField("comment_count")
    private Integer commentCount;
    
                    @ApiModelProperty("退货数量")
    @TableField("return_order_count")
    private Integer returnOrderCount;
    
                    @ApiModelProperty("登录次数")
    @TableField("login_count")
    private Integer loginCount;
    
                    @ApiModelProperty("关注数量")
    @TableField("attend_count")
    private Integer attendCount;
    
                    @ApiModelProperty("粉丝数量")
    @TableField("fans_count")
    private Integer fansCount;
    
                    @ApiModelProperty("收藏的商品数量")
    @TableField("collect_product_count")
    private Integer collectProductCount;
    
                    @ApiModelProperty("收藏的专题活动数量")
    @TableField("collect_subject_count")
    private Integer collectSubjectCount;
    
                    @ApiModelProperty("收藏的评论数量")
    @TableField("collect_comment_count")
    private Integer collectCommentCount;
    
                    @ApiModelProperty("邀请的朋友数量")
    @TableField("invite_friend_count")
    private Integer inviteFriendCount;
    

}