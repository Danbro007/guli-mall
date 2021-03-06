package com.danbro.auth.controller.dto;

import java.util.List;
import cn.hutool.core.annotation.Alias;
import com.danbro.common.dto.UmsMemberVo;
import com.danbro.common.interfaces.Converter;
import lombok.Data;

/**
 * @Classname WeChatUserInfo
 * @Description TODO 通过微信的 accessToken和 openid 返回的微信用户信息
 * @Date 2021/1/6 14:31
 * @Author Danrbo
 */
@Data
public class WeChatUserInfoDto implements Converter<UmsMemberVo,WeChatUserInfoDto> {
    @Alias("socialUid")
    private String openid;
    private String nickname;

    @Alias("gender")
    private Integer sex;

    private String city;
    @Alias("city")
    private String province;
    private String country;
    /**
     * 用户头像
     */
    @Alias("header")
    private String headImgUrl;
    private List<String> privilege;
    private String unionid;

    @Override
    public WeChatUserInfoDto convertToVo(UmsMemberVo umsMemberVo) {
        return null;
    }

    @Override
    public UmsMemberVo convertToEntity() {
        return null;
    }
}
