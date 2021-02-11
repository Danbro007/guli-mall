package com.danbro.member.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.member.controller.vo.UmsMemberVo;
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
    Pagination<UmsMemberVo, UmsMember> getMemberList(PageParam<UmsMember> pageParam, String key);
}