package com.danbro.member.controller;

import com.danbro.common.dto.UmsMemberVo;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.member.controller.vo.MemberLoginParamVo;
import com.danbro.member.controller.vo.MemberRegisterParamVo;
import com.danbro.member.entity.UmsMember;
import com.danbro.member.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Api(tags = "会员(UmsMember)")
@Validated
@RestController
@AllArgsConstructor
@Setter
@RequestMapping("member")
public class UmsMemberController {
    private UmsMemberService umsMemberService;

    @ApiOperation("分页查询会员")
    @GetMapping("member/list")
    public ResultPageBean<UmsMemberVo, UmsMember> getMemberList(@RequestParam("page") Long page,
                                                                @RequestParam("limit") Long limit,
                                                                @RequestParam(value = "key", required = false) String key) {
        PageParam<UmsMember> pageParam = new PageParam<UmsMember>().setPage(page).setLimit(limit);
        return ResultPageBean.ofSuccess(umsMemberService.getMemberList(pageParam, key));
    }

    @ApiOperation("添加会员")
    @PostMapping("member/register")
    public ResultBean<UmsMemberVo> insertMember(@RequestBody MemberRegisterParamVo memberRegisterParamVo) {
        return ResultBean.ofSuccess(umsMemberService.insertMember(memberRegisterParamVo));
    }

    @ApiOperation("会员登录")
    @PostMapping("member/login")
    public ResultBean<UmsMemberVo> loginMember(@RequestBody MemberLoginParamVo memberLoginParamVo) {
        return ResultBean.ofSuccess(umsMemberService.getMember(memberLoginParamVo));
    }

    @ApiOperation("微信用户登录")
    @PostMapping("wx/login")
    public ResultBean<UmsMemberVo> wxLogin(@RequestBody UmsMemberVo umsMemberVo) {
        return ResultBean.ofSuccess(umsMemberService.wxLogin(umsMemberVo));
    }
}