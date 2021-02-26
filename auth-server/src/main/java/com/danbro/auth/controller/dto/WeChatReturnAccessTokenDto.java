package com.danbro.auth.controller.dto;

import lombok.Data;

/**
 * @Classname WeChatReturnAccessTokenDto
 * @Description TODO 微信返回的 token 信息
 * @Date 2021/1/6 14:19
 * @Author Danrbo
 */
@Data
public class WeChatReturnAccessTokenDto {
    /**
     * 接口调用凭证
     */
    private String accessToken;
    /**
     * access_token接口调用凭证超时时间，单位（秒）
     */
    private Integer expiresIn;
    /**
     * 用户刷新access_token
     */
    private String refreshToken;
    /**
     * 授权用户唯一标识
     */
    private String openid;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;
    private String unionid;
}
