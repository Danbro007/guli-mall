package com.danbro.product.mapper;
 
import com.danbro.product.entity.PmsBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 品牌(PmsBrand)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 22:02:42
 */
@Mapper
public interface PmsBrandMapper extends BaseMapper<PmsBrand>{
 
}