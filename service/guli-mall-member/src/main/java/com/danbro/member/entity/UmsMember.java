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
@ApiModel("会员")
public class UmsMember implements Serializable {
    private static final long serialVersionUID = 994680222846836310L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("会员等级id")
    @TableField("level_id")
    private Long levelId;
    
                    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;
    
                    @ApiModelProperty("密码")
    @TableField("password")
    private String password;
    
                    @ApiModelProperty("昵称")
    @TableField("nickname")
    private String nickname;
    
                    @ApiModelProperty("手机号码")
    @TableField("mobile")
    private String mobile;
    
                    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;
    
                    @ApiModelProperty("头像")
    @TableField("header")
    private String header;
    
                    @ApiModelProperty("性别")
    @TableField("gender")
    private Object gender;
    
                    @ApiModelProperty("生日")
    @TableField("birth")
    private Object birth;
    
                    @ApiModelProperty("所在城市")
    @TableField("city")
    private String city;
    
                    @ApiModelProperty("职业")
    @TableField("job")
    private String job;
    
                    @ApiModelProperty("个性签名")
    @TableField("sign")
    private String sign;
    
                    @ApiModelProperty("用户来源")
    @TableField("source_type")
    private Object sourceType;
    
                    @ApiModelProperty("积分")
    @TableField("integration")
    private Integer integration;
    
                    @ApiModelProperty("成长值")
    @TableField("growth")
    private Integer growth;
    
                    @ApiModelProperty("启用状态")
    @TableField("status")
    private Object status;
    
                    @ApiModelProperty("注册时间")
    @TableField("create_time")
    private Date createTime;
    

}