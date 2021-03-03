package com.danbro.member.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.member.controller.vo.MemberLoginParamVo;
import com.danbro.member.controller.vo.MemberRegisterParamVo;
import com.danbro.common.dto.UmsMemberDto;
import com.danbro.member.entity.UmsMember;


/**
 * 会员(UmsMember)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
public interface UmsMemberService extends IService<UmsMember> {
    /**
     * 分页查询会员等级
     *
     * @param pageParam 分页条件
     * @param key       关键字
     * @return 会员等级列表
     */
    Pagination<UmsMemberDto, UmsMember> getMemberList(PageParam<UmsMember> pageParam, String key);

    /**
     * 添加会员
     *
     * @param memberRegisterParamVo 添加会员的参数
     * @return 添加完毕后的会员参数
     */
    UmsMemberDto insertMember(MemberRegisterParamVo memberRegisterParamVo);

    /**
     * 会员中有没有与当前会员名相同的
     *
     * @param username 会员名
     * @return 是否相同的结果
     */
    Boolean userNameIsExist(String username);

    /**
     * 会员中有没有与当前会员手机号相同的
     *
     * @param phone 手机号
     * @return 是否相同的结果
     */
    Boolean phoneIsExist(String phone);

    /**
     * 用户登录
     *
     * @param memberLoginParamVo 用户的用户名或者手机号和密码
     * @return 用户信息
     */
    UmsMemberDto getMember(MemberLoginParamVo memberLoginParamVo);

    /**
     * 微信用户登录
     * @param umsMemberDto 用户参数
     * @return
     */
    UmsMemberDto wxLogin(UmsMemberDto umsMemberDto);
}