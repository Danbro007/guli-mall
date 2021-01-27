package com.danbro.product.mapper;
 
import com.danbro.product.entity.PmsSkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * sku信息(PmsSkuInfo)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 22:02:43
 */
@Mapper
public interface PmsSkuInfoMapper extends BaseMapper<PmsSkuInfo>{
 
}