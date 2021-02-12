package com.danbro.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.member.controller.vo.UmsMemberLevelVo;
import com.danbro.member.entity.UmsMemberLevel;
import com.danbro.member.mapper.UmsMemberLevelMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.member.service.UmsMemberLevelService;
import org.springframework.stereotype.Service;

/**
 * 会员等级(UmsMemberLevel)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Service
public class UmsMemberLevelServiceImpl extends ServiceImpl<UmsMemberLevelMapper, UmsMemberLevel> implements UmsMemberLevelService {

    @Override
    public Pagination<UmsMemberLevelVo, UmsMemberLevel> getMemberLevelList(PageParam<UmsMemberLevel> pageParam, String key) {
        LambdaQueryWrapper<UmsMemberLevel> queryWrapper = new LambdaQueryWrapper<>();
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like(UmsMemberLevel::getName, key);
        }
        return new Pagination<>(this.page(new Query<UmsMemberLevel>().getPage(pageParam), queryWrapper), UmsMemberLevelVo.class);
    }

    @Override
    public UmsMemberLevelVo insertMemberLevel(UmsMemberLevelVo memberLevelVo) {
        return MyCurdUtils.insertOrUpdate(memberLevelVo, this.save(memberLevelVo.convertToEntity()), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public UmsMemberLevelVo updateMemberLevel(UmsMemberLevelVo memberLevelVo) {
        return MyCurdUtils.insertOrUpdate(memberLevelVo, this.updateById(memberLevelVo.convertToEntity()), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public UmsMemberLevelVo getMemberLevelInfoById(Long memberLevelId) {
        UmsMemberLevelVo umsMemberLevelVo = UmsMemberLevelVo.builder().build().convertToVo(this.getById(memberLevelId));
        return MyCurdUtils.select(umsMemberLevelVo, ResponseCode.NOT_FOUND, true);
    }

    @Override
    public UmsMemberLevelVo getMemberLevelInfoByName(String memberLevelName) {
        UmsMemberLevel memberLevel = this.getOne(new QueryWrapper<UmsMemberLevel>().lambda().eq(UmsMemberLevel::getName, memberLevelName));
        UmsMemberLevelVo memberLevelVo = UmsMemberLevelVo.builder().build().convertToVo(memberLevel);
        return MyCurdUtils.select(memberLevelVo,ResponseCode.NOT_FOUND);
    }
}