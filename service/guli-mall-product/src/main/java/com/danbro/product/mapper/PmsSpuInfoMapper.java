package com.danbro.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.product.controller.vo.front.SkuItemVo;
import com.danbro.product.entity.PmsSpuInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * spu信息(PmsSpuInfo)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Mapper
public interface PmsSpuInfoMapper extends BaseMapper<PmsSpuInfo> {

    List<SkuItemVo.SkuSaleAttrValue> getSaleAttrList(Long spuId);
}