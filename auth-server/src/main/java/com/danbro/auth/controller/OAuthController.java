package com.danbro.auth.controller;

import javax.annotation.Resource;
import com.danbro.auth.config.WeChatProperties;
import com.danbro.auth.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname OAuthController
 * @Description TODO
 * @Date 2021/2/26 19:31
 * @Created by Administrator
 */
@Controller
@RequestMapping("/api/ucenter/wx")
public class OAuthController {
    @Resource
    AuthService authService;

    @Autowired
    WeChatProperties weChatProperties;

    @ApiOperation("返回微信登录的二维码")
    @GetMapping("login")
    public String getWxCode() {
        String loginUrl = weChatProperties.getQrCodeUrl();
        return String.format("redirect:%s", loginUrl);
    }


    @ApiOperation("返回微信登录的二维码")
    @GetMapping("callback")
    public String callback(String code) {
        return authService.wxLogin(code);
    }
}
