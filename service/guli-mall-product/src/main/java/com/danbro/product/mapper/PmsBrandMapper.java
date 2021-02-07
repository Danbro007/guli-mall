package com.danbro.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.product.entity.PmsBrand;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌(PmsBrand)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Mapper
public interface PmsBrandMapper extends BaseMapper<PmsBrand>{
 
}