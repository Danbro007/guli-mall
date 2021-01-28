package com.danbro.coupon.entity;

import java.util.Locale;
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
@ApiModel("首页轮播广告")
public class SmsHomeAdv implements Serializable {
    private static final long serialVersionUID = -65617232874401237L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("图片地址")
    private String pic;

    @ApiModelProperty("开始时间")
    private Locale startTime;

    @ApiModelProperty("结束时间")
    private Locale endTime;

    @ApiModelProperty("状态")
    private Object status;

    @ApiModelProperty("点击数")
    private Integer clickCount;

    @ApiModelProperty("广告详情连接地址")
    private String url;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("发布者")
    private Long publisherId;

    @ApiModelProperty("审核者")
    private Long authId;


}