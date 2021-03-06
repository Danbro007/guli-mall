package com.danbro.auth.rpc;

import com.danbro.auth.controller.vo.MemberLoginParamVo;
import com.danbro.auth.controller.vo.MemberRegisterParamVo;
import com.danbro.common.dto.UmsMemberVo;
import com.danbro.common.entity.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Danrbo
 * @Classname UmsClient
 * @Description TODO 会员服务客户端
 * @Date 2021/2/26 13:12
 */
@FeignClient(value = "service-ums")
public interface UmsClient {
    /**
     * 会员注册
     *
     * @param memberRegisterParamVo 会员的参数
     * @return 添加完毕后的会员数据
     */
    @PostMapping("member/member/register")
    ResultBean<UmsMemberVo> registerMember(@RequestBody MemberRegisterParamVo memberRegisterParamVo);

    /**
     * 会员登录
     *
     * @param memberLoginParamVo 登录会员的参数
     * @return 登录成功返回的会员信息
     */
    @PostMapping("member/member/login")
    ResultBean<UmsMemberVo> loginMember(@RequestBody MemberLoginParamVo memberLoginParamVo);

    /**
     * 微信用户登录
     *
     * @param memberVo
     * @return
     */
    @PostMapping("member/wx/login")
    ResultBean<UmsMemberVo> weChatUserLogin(@RequestBody UmsMemberVo memberVo);

}
