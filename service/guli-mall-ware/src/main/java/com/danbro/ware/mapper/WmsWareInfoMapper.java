package com.danbro.ware.mapper;
 
import com.danbro.ware.entity.WmsWareInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 仓库信息(WmsWareInfo)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Mapper
public interface WmsWareInfoMapper extends BaseMapper<WmsWareInfo>{
 
}