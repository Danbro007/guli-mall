package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.product.controller.vo.PmsAttrAttrgroupRelationVo;
import com.danbro.product.entity.PmsAttrAttrgroupRelation;
import com.danbro.product.mapper.PmsAttrAttrgroupRelationMapper;
import com.danbro.product.service.PmsAttrAttrgroupRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 属性&属性分组关联(PmsAttrAttrgroupRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsAttrAttrgroupRelationServiceImpl extends ServiceImpl<PmsAttrAttrgroupRelationMapper, PmsAttrAttrgroupRelation> implements PmsAttrAttrgroupRelationService {

    @Override
    public PmsAttrAttrgroupRelationVo insertAttrAttrRelation(PmsAttrAttrgroupRelationVo relation) {
        return MyCurdUtils.insertOrUpdate(relation, this.save(relation.convertToEntity()), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public List<PmsAttrAttrgroupRelationVo> batchInsertAttrAttrRelation(List<PmsAttrAttrgroupRelationVo> relationVoList) {
        List<PmsAttrAttrgroupRelation> relations = relationVoList.stream().map(PmsAttrAttrgroupRelationVo::convertToEntity).collect(Collectors.toList());
        return MyCurdUtils.batchInsertOrUpdate(relationVoList, this.saveBatch(relations), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public PmsAttrAttrgroupRelationVo getAttrAttrRelationByAttrId(Long attrId, Boolean throwException) {
        PmsAttrAttrgroupRelation relation = this.getOne(new QueryWrapper<PmsAttrAttrgroupRelation>().lambda().eq(PmsAttrAttrgroupRelation::getAttrId, attrId));
        PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = MyCurdUtils.select(relation, ResponseCode.NOT_FOUND, throwException);
        if (MyObjectUtils.isNotNull(pmsAttrAttrgroupRelation)) {
            return PmsAttrAttrgroupRelationVo.builder().build().convertToVo(pmsAttrAttrgroupRelation);
        }
        return null;
    }

    @Override
    public void batchDeleteByAttrId(Long[] ids, Boolean throwException) {
        MyCurdUtils.batchDelete(this.remove(new QueryWrapper<PmsAttrAttrgroupRelation>()
                        .lambda()
                        .in(PmsAttrAttrgroupRelation::getAttrId, Arrays.asList(ids))),
                ResponseCode.DELETE_FAILURE);
    }

    @Override
    public void batchDeleteByAttrGroupId(Long[] ids, Boolean throwException) {
        // 如果是销售属性删除则到关系表里删除失败也不抛出异常。
        MyCurdUtils.batchDelete(this.remove(new QueryWrapper<PmsAttrAttrgroupRelation>()
                        .lambda()
                        .in(PmsAttrAttrgroupRelation::getAttrGroupId, Arrays.asList(ids))),
                ResponseCode.DELETE_FAILURE,
                throwException);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteByAttrIdAndAttrGroupId(List<PmsAttrAttrgroupRelationVo> paramList) {
        paramList.forEach(e -> {
            MyCurdUtils.delete(this.remove(new QueryWrapper<PmsAttrAttrgroupRelation>().
                            lambda().
                            eq(PmsAttrAttrgroupRelation::getAttrId, e.getAttrId()).
                            eq(PmsAttrAttrgroupRelation::getAttrGroupId, e.getAttrGroupId())),
                    ResponseCode.DELETE_FAILURE);
        });
    }
}