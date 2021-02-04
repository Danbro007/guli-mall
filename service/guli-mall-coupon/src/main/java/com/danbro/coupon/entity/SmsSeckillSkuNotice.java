package com.danbro.coupon.entity;

import java.util.Date;
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
@ApiModel("秒杀商品通知订阅")
public class SmsSeckillSkuNotice implements Serializable {
    private static final long serialVersionUID = 956670589457390714L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("member_id")
    private Long memberId;

    @ApiModelProperty("sku_id")
    private Long skuId;

    @ApiModelProperty("活动场次id")
    private Long sessionId;

    @ApiModelProperty("订阅时间")
    private Date subcribeTime;

    @ApiModelProperty("发送时间")
    private Date sendTime;

    @ApiModelProperty("通知方式[0-短信，1-邮件]")
    private Integer noticeType;


}