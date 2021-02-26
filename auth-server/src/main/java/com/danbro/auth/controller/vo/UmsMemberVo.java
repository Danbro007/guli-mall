package com.danbro.auth.controller.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Danrbo
 * @Classname UmsMeberVo
 * @Description TODO
 * @Date 2021/2/26 13:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class UmsMemberVo implements Serializable {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("会员等级id")
    private Long levelId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String header;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("生日")
    private Date birth;

    @ApiModelProperty("所在城市")
    private String city;

    @ApiModelProperty("职业")
    private String job;

    @ApiModelProperty("个性签名")
    private String sign;

    @ApiModelProperty("用户来源")
    private Integer sourceType;

    @ApiModelProperty("积分")
    private Integer integration;

    @ApiModelProperty("成长值")
    private Integer growth;

    @ApiModelProperty("启用状态")
    private Boolean status;

    @ApiModelProperty("注册时间")
    private Date createTime;

    @ApiModelProperty("社交账户ID")
    private String socialUid;

    @ApiModelProperty("token")
    private String accessToken;

    @ApiModelProperty("过期时间")
    private Integer expiresIn;


}