package com.danbro.product.entity;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 22:02:43
 */
@Data
@Accessors(chain = true)
@ApiModel("商品评价")
public class PmsSpuComment implements Serializable {
    private static final long serialVersionUID = -52717702956918570L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("sku_id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("spu_id")
    @TableField("spu_id")
    private Long spuId;
    
                    @ApiModelProperty("商品名字")
    @TableField("spu_name")
    private String spuName;
    
                    @ApiModelProperty("会员昵称")
    @TableField("member_nick_name")
    private String memberNickName;
    
                    @ApiModelProperty("星级")
    @TableField("star")
    private Object star;
    
                    @ApiModelProperty("会员ip")
    @TableField("member_ip")
    private String memberIp;
    
                    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("显示状态[0-不显示，1-显示]")
    @TableField("show_status")
    private Object showStatus;
    
                    @ApiModelProperty("购买时属性组合")
    @TableField("spu_attributes")
    private String spuAttributes;
    
                    @ApiModelProperty("点赞数")
    @TableField("likes_count")
    private Integer likesCount;
    
                    @ApiModelProperty("回复数")
    @TableField("reply_count")
    private Integer replyCount;
    
                    @ApiModelProperty("评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
    @TableField("resources")
    private String resources;
    
                    @ApiModelProperty("内容")
    @TableField("content")
    private String content;
    
                    @ApiModelProperty("用户头像")
    @TableField("member_icon")
    private String memberIcon;
    
                    @ApiModelProperty("评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
    @TableField("comment_type")
    private Object commentType;
    

}