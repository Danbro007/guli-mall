package com.danbro.product.service.impl;

import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.product.controller.vo.PmsAttrVo;
import com.danbro.product.entity.PmsAttr;
import com.danbro.product.entity.PmsAttrAttrgroupRelation;
import com.danbro.product.entity.PmsCategory;
import com.danbro.product.mapper.PmsAttrMapper;
import com.danbro.product.service.PmsAttrAttrgroupRelationService;
import com.danbro.product.service.PmsAttrGroupService;
import com.danbro.product.service.PmsAttrService;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品属性(PmsAttr)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsAttrServiceImpl extends ServiceImpl<PmsAttrMapper, PmsAttr> implements PmsAttrService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private PmsCategoryService pmsCategoryService;
    @Autowired
    private PmsAttrGroupService pmsAttrGroupService;

    @Autowired
    PmsAttrAttrgroupRelationService attrgroupRelationService;

    @Override
    public Pagination<PmsAttrVo, PmsAttr> queryPage(PageParam<PmsAttr> pageParam, String key, Long categoryId) {
        IPage<PmsAttr> page;
        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<>();
        // 返回所有
        if (MyObjectUtils.isNotEmpty(categoryId) && categoryId > 0) {
            queryWrapper.eq("catelog_id", categoryId);
        }
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like("attr_name", key).or().like("attr_id", key);
        }

        page = this.page(new Query<PmsAttr>().getPage(pageParam), queryWrapper);
        return new Pagination<>(page, PmsAttrVo.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrVo insertAttr(PmsAttrVo param) {
        PmsAttrAttrgroupRelation attrgroupRelation = new PmsAttrAttrgroupRelation();
        MyBeanUtils.copyProperties(param, attrgroupRelation);
        PmsAttr pmsAttr = param.convertToEntity();
        this.save(pmsAttr);
        attrgroupRelation.setAttrId(pmsAttr.getAttrId());
        attrgroupRelationService.save(attrgroupRelation);
        return param;
    }

    @Override
    public PmsAttrVo updateAttr(PmsAttrVo param) {
        PmsAttrAttrgroupRelation attrgroupRelation = new PmsAttrAttrgroupRelation();
        MyBeanUtils.copyProperties(param, attrgroupRelation);
        PmsAttr pmsAttr = param.convertToEntity();
        this.updateById(pmsAttr);
        attrgroupRelation.setAttrId(pmsAttr.getAttrId());
        attrgroupRelationService.updateById(attrgroupRelation);
        return param;
    }

    @Override
    public PmsAttrVo getAttrById(Long attrId) {
        PmsAttr pmsAttr = MyCurdUtils.selectOne(this.getById(attrId), ResponseCode.NOT_FOUND);
        PmsAttrVo pmsAttrVo = PmsAttrVo.builder().build().convertToVo(pmsAttr);
        if (MyObjectUtils.isNotEmpty(pmsAttr.getCatelogId())) {
            String[] cateLogPath = pmsCategoryService.findCateLogPath(pmsAttr.getCatelogId());
            pmsAttrVo.setCatelogPath(cateLogPath);
        }
        return pmsAttrVo;
    }
}