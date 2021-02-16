package com.danbro.product.service;


import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsAttrDetailVo;
import com.danbro.product.controller.vo.PmsProductAttrValueVo;
import com.danbro.product.entity.PmsProductAttrValue;


/**
 * spu属性值(PmsProductAttrValue)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsProductAttrValueService extends IService<PmsProductAttrValue> {
    /**
     * 批量添加属性
     *
     * @param attrValueList 添加的属性列表
     * @return 添加完毕后的属性结果
     */
    List<PmsProductAttrValueVo> batchSave(List<PmsProductAttrValueVo> attrValueList);

    /**
     * 查询出 Spu 相关的所有属性
     *
     * @param spuId SpuId
     * @return 相关属性
     */
    List<PmsProductAttrValueVo> getAttrValueListBySpuId(Long spuId);
}