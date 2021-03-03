package com.danbro.auth.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import cn.hutool.http.HttpUtil;
import com.danbro.auth.config.WeChatProperties;
import com.danbro.auth.controller.dto.WeChatReturnAccessTokenDto;
import com.danbro.auth.controller.dto.WeChatUserInfoDto;
import com.danbro.auth.enums.MemberSourceType;
import com.danbro.auth.rpc.UmsClient;
import com.danbro.auth.service.AuthService;
import com.danbro.common.dto.UmsMemberDto;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyJSONUtils;
import com.danbro.common.utils.MyStrUtils;
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

    @Autowired
    UmsClient umsClient;

    @ApiOperation("返回微信登录的二维码")
    @GetMapping("login")
    public String getWxCode() {
        String loginUrl = weChatProperties.getQrCodeUrl();
        return String.format("redirect:%s", loginUrl);
    }


    @ApiOperation("返回微信登录的二维码")
    @GetMapping("callback")
    public String callback(String code, HttpSession session) {
        if (MyStrUtils.isNotEmpty(code)) {
            String url = String.format(weChatProperties.getTokenUrl(), weChatProperties.getAppId(), weChatProperties.getAppSecret(), code);
            String tokenInfo = HttpUtil.get(url);
            if (MyStrUtils.isNotEmpty(tokenInfo)) {
                WeChatReturnAccessTokenDto tokenDto = MyJSONUtils.parseObject(tokenInfo, WeChatReturnAccessTokenDto.class);
                if (MyStrUtils.isNotEmpty(tokenDto.getAccessToken()) && MyStrUtils.isNotEmpty(tokenDto.getOpenid())) {
                    String userInfo = HttpUtil.get(String.format(weChatProperties.getWeChatUserInfoUrl(), tokenDto.getAccessToken(), tokenDto.getOpenid()));
                    WeChatUserInfoDto weChatUserInfoDto = MyJSONUtils.parseObject(userInfo, WeChatUserInfoDto.class);
                    UmsMemberDto memberVo = new UmsMemberDto();
                    MyBeanUtils.copyProperties(weChatUserInfoDto, memberVo);
                    MyBeanUtils.copyProperties(tokenDto, memberVo);
                    memberVo.setSourceType(MemberSourceType.WECHAT);
                    memberVo.setStatus(true);
                    // 登录返回token
                    // Todo session存储后会找不到session
                    UmsMemberDto umsMemberDto = MyCurdUtils.rpcResultHandle(umsClient.weChatUserLogin(memberVo));
                    session.setAttribute("loginUser", umsMemberDto);
                    return "redirect:http://gulimall.com";
                }
            }
        }
        // 其他失败都重定向到登录页面
        return "redirect:http://auth.gulimall.com/login.html";
    }
}
