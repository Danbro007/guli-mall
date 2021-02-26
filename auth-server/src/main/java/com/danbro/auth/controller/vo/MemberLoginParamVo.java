package com.danbro.auth.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Danrbo
 * @Classname MemberLoginParamVo
 * @Description TODO 会员登录参数
 * @Date 2021/2/26 16:09
 */
@Data
public class MemberLoginParamVo {
    @NotBlank(message = "用户名或者手机号不能为空！")
    private String loginacct;

    @NotBlank(message = "密码不能为空！")
    private String password;
}
