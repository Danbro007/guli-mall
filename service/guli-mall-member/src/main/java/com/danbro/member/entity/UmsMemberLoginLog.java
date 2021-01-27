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
@ApiModel("会员登录记录")
public class UmsMemberLoginLog implements Serializable {
    private static final long serialVersionUID = 914435019455974906L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("member_id")
    @TableField("member_id")
    private Long memberId;
    
                    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("ip")
    @TableField("ip")
    private String ip;
    
                    @ApiModelProperty("city")
    @TableField("city")
    private String city;
    
                    @ApiModelProperty("登录类型[1-web，2-app]")
    @TableField("login_type")
    private Object loginType;
    

}