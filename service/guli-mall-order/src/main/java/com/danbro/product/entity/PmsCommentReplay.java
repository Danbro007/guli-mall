package com.danbro.product.entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Data
@Accessors(chain = true)
@ApiModel("商品评价回复关系")
public class PmsCommentReplay implements Serializable {
    private static final long serialVersionUID = -89992921222162864L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("评论id")
    @TableField("comment_id")
    private Long commentId;
    
                    @ApiModelProperty("回复id")
    @TableField("reply_id")
    private Long replyId;
    

}