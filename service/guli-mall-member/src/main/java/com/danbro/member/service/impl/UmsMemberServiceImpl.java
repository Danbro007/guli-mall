package com.danbro.member.service.impl;
 
import com.danbro.member.entity.UmsMember;
import com.danbro.member.mapper.UmsMemberMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    
}