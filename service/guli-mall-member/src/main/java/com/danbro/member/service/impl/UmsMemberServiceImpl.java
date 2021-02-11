package com.danbro.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.member.controller.vo.UmsMemberVo;
import com.danbro.member.entity.UmsMember;
import com.danbro.member.mapper.UmsMemberMapper;
import com.danbro.member.service.UmsMemberService;
import org.springframework.stereotype.Service;

/**
 * 会员(UmsMember)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {

    @Override
    public Pagination<UmsMemberVo, UmsMember> getMemberList(PageParam<UmsMember> pageParam, String key) {
        LambdaQueryWrapper<UmsMember> wrapper = new LambdaQueryWrapper<>();
        if (MyStrUtils.isNotEmpty(key)) {
            wrapper.like(UmsMember::getNickname, key).or().like(UmsMember::getUsername, key);
        }
        return new Pagination<>(this.page(new Query<UmsMember>().getPage(pageParam), wrapper), UmsMemberVo.class);
    }
}