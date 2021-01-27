package com.danbro.ware.mapper;
 
import com.danbro.ware.entity.WmsPurchase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 采购信息(WmsPurchase)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:31:11
 */
@Mapper
public interface WmsPurchaseMapper extends BaseMapper<WmsPurchase>{
 
}