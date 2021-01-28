package com.danbro.member.mapper;
 
import com.danbro.member.entity.UmsMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 会员(UmsMember)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Mapper
public interface UmsMemberMapper extends BaseMapper<UmsMember>{
 
}