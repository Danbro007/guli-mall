package com.danbro.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.*;
import com.danbro.member.controller.vo.MemberRegisterParamVo;
import com.danbro.member.controller.vo.UmsMemberLevelVo;
import com.danbro.member.controller.vo.UmsMemberVo;
import com.danbro.member.entity.UmsMember;
import com.danbro.member.mapper.UmsMemberMapper;
import com.danbro.member.service.UmsMemberLevelService;
import com.danbro.member.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 会员(UmsMember)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Service
public class UmsMemberServiceImpl extends ServiceImpl<UmsMemberMapper, UmsMember> implements UmsMemberService {


    @Autowired
    UmsMemberLevelService umsMemberLevelService;

    @Override
    public Pagination<UmsMemberVo, UmsMember> getMemberList(PageParam<UmsMember> pageParam, String key) {
        LambdaQueryWrapper<UmsMember> wrapper = new LambdaQueryWrapper<>();
        if (MyStrUtils.isNotEmpty(key)) {
            wrapper.like(UmsMember::getNickname, key).or().like(UmsMember::getUsername, key);
        }
        return new Pagination<>(this.page(new Query<UmsMember>().getPage(pageParam), wrapper), UmsMemberVo.class);
    }

    @Override
    public UmsMemberVo insertMember(MemberRegisterParamVo paramVo) {
        // 校验用户名有没有冲突
        if (this.userNameIsExist(paramVo.getUserName())) {
            throw new GuliMallException(ResponseCode.USERNAME_IS_EXIST);
        }
        // 校验手机号有没有冲突
        if (this.phoneIsExist(paramVo.getPhone())) {
            throw new GuliMallException(ResponseCode.PHONE_IS_EXIST);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(paramVo.getPassword());
        UmsMember umsMember = new UmsMember()
                .setUsername(paramVo.getUserName())
                .setMobile(paramVo.getPhone())
                .setPassword(encodedPassword);
        UmsMemberLevelVo defaultMemberLevel = umsMemberLevelService.getDefaultMemberLevel();
        // 会员的默认等级
        umsMember.setLevelId(defaultMemberLevel.getId());
        boolean save = this.save(umsMember);
        UmsMemberVo umsMemberVo = ConvertUtils.convert(umsMember, UmsMemberVo.class);
        return MyCurdUtils.insertOrUpdate(umsMemberVo, save, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public Boolean userNameIsExist(String username) {
        return this.count(new QueryWrapper<UmsMember>().lambda().eq(UmsMember::getUsername, username)) > 0;
    }

    @Override
    public Boolean phoneIsExist(String phone) {
        return this.count(new QueryWrapper<UmsMember>().lambda().eq(UmsMember::getMobile, phone)) > 0;
    }


}