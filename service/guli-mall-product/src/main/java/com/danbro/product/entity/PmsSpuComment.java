package com.danbro.product.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Data
@Accessors(chain = true)
@ApiModel("商品评价")
public class PmsSpuComment implements Serializable {
    private static final long serialVersionUID = 708712941889368467L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("sku_id")
    private Long skuId;

    @ApiModelProperty("spu_id")
    private Long spuId;

    @ApiModelProperty("商品名字")
    private String spuName;

    @ApiModelProperty("会员昵称")
    private String memberNickName;

    @ApiModelProperty("星级")
    private Integer star;

    @ApiModelProperty("会员ip")
    private String memberIp;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("显示状态[0-不显示，1-显示]")
    private Boolean showStatus;

    @ApiModelProperty("购买时属性组合")
    private String spuAttributes;

    @ApiModelProperty("点赞数")
    private Integer likesCount;

    @ApiModelProperty("回复数")
    private Integer replyCount;

    @ApiModelProperty("评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
    private String resources;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("用户头像")
    private String memberIcon;

    @ApiModelProperty("评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
    private Integer commentType;


}