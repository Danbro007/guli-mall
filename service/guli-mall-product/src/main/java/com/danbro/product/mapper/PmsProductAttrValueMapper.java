package com.danbro.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.product.controller.vo.front.SkuItemVo;
import com.danbro.product.entity.PmsProductAttrValue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * spu属性值(PmsProductAttrValue)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Mapper
public interface PmsProductAttrValueMapper extends BaseMapper<PmsProductAttrValue> {

    List<SkuItemVo.SpuAttrGroupVo> getSpuAttrGroupBySpuId(Long spuId);
}