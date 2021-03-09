package com.danbro.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Data
@Accessors(chain = true)
@ApiModel("商品评价回复关系")
public class PmsCommentReplay implements Serializable {
    private static final long serialVersionUID = 263839815873648485L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("评论id")
    private Long commentId;

    @ApiModelProperty("回复id")
    private Long replyId;


}