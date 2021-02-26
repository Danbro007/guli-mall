package com.danbro.member.controller.vo;

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

    private String userName;

    private String password;

    private String phone;

    private String code;
}
