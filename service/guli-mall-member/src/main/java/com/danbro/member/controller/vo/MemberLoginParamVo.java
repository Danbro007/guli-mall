package com.danbro.member.controller.vo;

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
    private String loginacct;
    private String password;
}
