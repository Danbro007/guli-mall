package com.danbro.member.entity;

import java.util.Locale;
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
@ApiModel("会员收藏的商品")
public class UmsMemberCollectSpu implements Serializable {
    private static final long serialVersionUID = 295855441860057458L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("spu_id")
    private Long spuId;

    @ApiModelProperty("spu_name")
    private String spuName;

    @ApiModelProperty("spu_img")
    private String spuImg;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("create_time")
    private Date createTime;


}