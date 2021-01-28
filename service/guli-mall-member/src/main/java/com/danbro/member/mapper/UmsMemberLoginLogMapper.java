package com.danbro.member.mapper;
 
import com.danbro.member.entity.UmsMemberLoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 会员登录记录(UmsMemberLoginLog)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Mapper
public interface UmsMemberLoginLogMapper extends BaseMapper<UmsMemberLoginLog>{
 
}