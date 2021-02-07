package com.danbro.member.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Data
@Accessors(chain = true)
@ApiModel("会员统计信息")
public class UmsMemberStatisticsInfo implements Serializable {
    private static final long serialVersionUID = 826787894825523855L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("累计消费金额")
    private BigDecimal consumeAmount;

    @ApiModelProperty("累计优惠金额")
    private BigDecimal couponAmount;

    @ApiModelProperty("订单数量")
    private Integer orderCount;

    @ApiModelProperty("优惠券数量")
    private Integer couponCount;

    @ApiModelProperty("评价数")
    private Integer commentCount;

    @ApiModelProperty("退货数量")
    private Integer returnOrderCount;

    @ApiModelProperty("登录次数")
    private Integer loginCount;

    @ApiModelProperty("关注数量")
    private Integer attendCount;

    @ApiModelProperty("粉丝数量")
    private Integer fansCount;

    @ApiModelProperty("收藏的商品数量")
    private Integer collectProductCount;

    @ApiModelProperty("收藏的专题活动数量")
    private Integer collectSubjectCount;

    @ApiModelProperty("收藏的评论数量")
    private Integer collectCommentCount;

    @ApiModelProperty("邀请的朋友数量")
    private Integer inviteFriendCount;


}