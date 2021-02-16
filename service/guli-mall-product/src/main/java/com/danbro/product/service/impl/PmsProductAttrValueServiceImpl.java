package com.danbro.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.product.controller.vo.PmsAttrDetailVo;
import com.danbro.product.controller.vo.PmsProductAttrValueVo;
import com.danbro.product.entity.PmsProductAttrValue;
import com.danbro.product.mapper.PmsProductAttrValueMapper;
import com.danbro.product.service.PmsAttrService;
import com.danbro.product.service.PmsProductAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * spu属性值(PmsProductAttrValue)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsProductAttrValueServiceImpl extends ServiceImpl<PmsProductAttrValueMapper, PmsProductAttrValue> implements PmsProductAttrValueService {

    @Autowired
    PmsAttrService pmsAttrService;

    @Override
    public List<PmsProductAttrValueVo> batchSave(List<PmsProductAttrValueVo> attrValueList) {
        // 先查出属性名然后再保存
        List<PmsProductAttrValue> pmsProductAttrValues = attrValueList.stream().map(PmsProductAttrValueVo::convertToEntity).collect(Collectors.toList());
        pmsProductAttrValues.forEach(attr -> {
            PmsAttrDetailVo attrDetailVo = pmsAttrService.getAttrById(attr.getAttrId());
            attr.setAttrName(attrDetailVo.getAttrName());
        });
        boolean saveBatch = this.saveBatch(pmsProductAttrValues);
        return MyCurdUtils.batchInsertOrUpdate(pmsProductAttrValues.stream().map(attr -> PmsProductAttrValueVo.builder().build().convertToVo(attr)).collect(Collectors.toList()), saveBatch, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public List<PmsProductAttrValueVo> getAttrValueListBySpuId(Long spuId) {
        List<PmsProductAttrValue> pmsProductAttrValues = MyCurdUtils.selectList(this.list(new QueryWrapper<PmsProductAttrValue>().lambda().eq(PmsProductAttrValue::getSpuId, spuId)), ResponseCode.NOT_FOUND);
        return pmsProductAttrValues.stream().map(productAttrValue -> PmsProductAttrValueVo.builder().build().convertToVo(productAttrValue)).collect(Collectors.toList());
    }

    @Override
    public List<PmsProductAttrValueVo> getAttrValueListBySpuIdWithShow(Long spuId) {
        return null;
    }
}