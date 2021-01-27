package com.danbro.product.mapper;
 
import com.danbro.product.entity.PmsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 商品三级分类(PmsCategory)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Mapper
public interface PmsCategoryMapper extends BaseMapper<PmsCategory>{
 
}