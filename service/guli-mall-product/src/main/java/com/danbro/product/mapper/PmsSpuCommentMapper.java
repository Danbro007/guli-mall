package com.danbro.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.product.entity.PmsSpuComment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价(PmsSpuComment)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Mapper
public interface PmsSpuCommentMapper extends BaseMapper<PmsSpuComment>{
 
}