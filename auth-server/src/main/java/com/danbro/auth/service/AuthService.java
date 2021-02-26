package com.danbro.auth.service;

import com.danbro.auth.controller.vo.MemberRegisterParamVo;

/**
 * @author Danrbo
 * @Classname AuthService
 * @Description TODO
 * @Date 2021/2/25 22:03
 */
public interface AuthService {
    /**
     * 发送验证码
     *
     * @param phone 手机号
     */
    void sendCode(String phone);

    /**
     * 注册会员
     *
     * @param registerParamVo 会员的参数
     * @return 返回的视图页面
     */
    String register(MemberRegisterParamVo registerParamVo);

    /**
     * 微信用户登录
     * @param code
     * @return
     */
    String wxLogin(String code);
}
