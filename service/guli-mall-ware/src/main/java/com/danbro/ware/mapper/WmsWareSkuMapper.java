package com.danbro.ware.mapper;
 
import com.danbro.ware.entity.WmsWareSku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 商品库存(WmsWareSku)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:31:11
 */
@Mapper
public interface WmsWareSkuMapper extends BaseMapper<WmsWareSku>{
 
}