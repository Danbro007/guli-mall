package com.danbro.auth.controller.vo;

import com.danbro.service.common.validtors.anotations.IsMobile;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Danrbo
 * @Classname MemberRegisterParamVo
 * @Description TODO 会员注册参数Vo
 * @Date 2021/2/26 11:18
 */
@Data
public class MemberRegisterParamVo implements Serializable {

    @Length(min = 6, max = 16, message = "用户名长度必须为6~16位！")
    private String userName;


    @Length(min = 6, max = 16, message = "密码长度必须为6~16位！")
    private String password;

    @IsMobile(message = "手机号必须为11位！")
    private String phone;

    @NotEmpty(message = "验证码不能为空！")
    private String code;
}
