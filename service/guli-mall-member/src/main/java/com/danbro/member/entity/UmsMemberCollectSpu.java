package com.danbro.member.entity;
import java.util.Date;
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
@ApiModel("会员收藏的商品")
public class UmsMemberCollectSpu implements Serializable {
    private static final long serialVersionUID = 421724026446615118L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("会员id")
    @TableField("member_id")
    private Long memberId;
    
                    @ApiModelProperty("spu_id")
    @TableField("spu_id")
    private Long spuId;
    
                    @ApiModelProperty("spu_name")
    @TableField("spu_name")
    private String spuName;
    
                    @ApiModelProperty("spu_img")
    @TableField("spu_img")
    private String spuImg;
    
                    @ApiModelProperty("create_time")
    @TableField("create_time")
    private Date createTime;
    

}