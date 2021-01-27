package com.danbro.member.mapper;
 
import com.danbro.member.entity.UmsIntegrationChangeHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 积分变化历史记录(UmsIntegrationChangeHistory)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:30:58
 */
@Mapper
public interface UmsIntegrationChangeHistoryMapper extends BaseMapper<UmsIntegrationChangeHistory>{
 
}