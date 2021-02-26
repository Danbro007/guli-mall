package com.danbro.auth.config;

import java.net.URLEncoder;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname WeChatProperties
 * @Description TODO
 * @Date 2021/2/26 19:50
 * @Created by Administrator
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties implements InitializingBean {
    private String appId;
    private String appSecret;
    private String redirectUrl;
    private String qrCodeUrl;
    private String tokenUrl;
    private String weChatUserInfoUrl;

    @Override
    public void afterPropertiesSet() throws Exception {
        qrCodeUrl = String.format("https://open.weixin.qq.com/connect/qrconnect?" +
                "appid=%s&" +
                "redirect_uri=%s&" +
                "response_type=code&" +
                "scope=snsapi_login&" +
                "state=atguigu#" +
                "wechat_redirect", appId, URLEncoder.encode(redirectUrl, "utf-8"));
        tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=%s&" +
                "secret=%s&" +
                "code=%s&" +
                "grant_type=authorization_code";

        weChatUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=%s" +
                "&openid=%s";
    }
}
