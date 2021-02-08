package com.danbro.product.service.impl;

import java.util.Arrays;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.product.controller.vo.PmsAttrAttrgroupRelationVo;
import com.danbro.product.entity.PmsAttrAttrgroupRelation;
import com.danbro.product.mapper.PmsAttrAttrgroupRelationMapper;
import com.danbro.product.service.PmsAttrAttrgroupRelationService;
import org.springframework.stereotype.Service;

/**
 * 属性&属性分组关联(PmsAttrAttrgroupRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsAttrAttrgroupRelationServiceImpl extends ServiceImpl<PmsAttrAttrgroupRelationMapper, PmsAttrAttrgroupRelation> implements PmsAttrAttrgroupRelationService {

    @Override
    public PmsAttrAttrgroupRelationVo insertAttrAttrRelation(PmsAttrAttrgroupRelationVo attrgroupRelation) {
        return MyCurdUtils.insertOrUpdate(attrgroupRelation, this.save(attrgroupRelation.convertToEntity()), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public PmsAttrAttrgroupRelationVo getAttrAttrRelationByAttrId(Long attrId) {
        QueryWrapper<PmsAttrAttrgroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id", attrId);
        PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation = this.getOne(queryWrapper);
        return PmsAttrAttrgroupRelationVo.builder().build().convertToVo(pmsAttrAttrgroupRelation);
    }

    @Override
    public void batchDeleteByAttrId(Long[] ids) {
        QueryWrapper<PmsAttrAttrgroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("attr_id", Arrays.asList(ids));
        MyCurdUtils.batchDelete(this.remove(queryWrapper), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public void batchDeleteByAttrGroupId(Long[] ids) {
        QueryWrapper<PmsAttrAttrgroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("attr_group_id", Arrays.asList(ids));
        MyCurdUtils.batchDelete(this.remove(queryWrapper), ResponseCode.DELETE_FAILURE);
    }
}