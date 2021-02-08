package com.danbro.product.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.common.utils.MyCurdUtils;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.Pagination;
import com.danbro.common.utils.Query;
import com.danbro.product.controller.vo.PmsAttrAttrgroupRelationVo;
import com.danbro.product.controller.vo.PmsAttrBaseInfoVo;
import com.danbro.product.controller.vo.PmsAttrDetailVo;
import com.danbro.product.controller.vo.PmsAttrGroupVo;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.entity.PmsAttr;
import com.danbro.product.entity.PmsAttrAttrgroupRelation;
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
    public Pagination<PmsAttrBaseInfoVo, PmsAttr> queryPage(PageParam<PmsAttr> pageParam, String key, Long categoryId) {
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
        List<PmsAttrBaseInfoVo> list = page.getRecords().stream().map(attr -> {
            PmsAttrBaseInfoVo infoVo = PmsAttrBaseInfoVo.builder().build().convertToVo(attr);
            // 找到属性对应的属性分组
            if (MyObjectUtils.isNotEmpty(attr.getAttrId())) {
                PmsAttrAttrgroupRelationVo relation = attrgroupRelationService.getAttrAttrRelationByAttrId(infoVo.getAttrId());
                if (MyObjectUtils.isNotEmpty(relation)) {
                    PmsAttrGroupVo groupInfo = pmsAttrGroupService.getAttrGroupInfo(relation.getAttrGroupId());
                    if (MyObjectUtils.isNotEmpty(groupInfo)) {
                        infoVo.setGroupName(groupInfo.getAttrGroupName());
                    }
                }
            }
            // 再查找属性对应的分类
            if (MyObjectUtils.isNotEmpty(attr.getCatelogId())) {
                PmsCategoryVo categoryInfo = pmsCategoryService.getCategoryInfo(attr.getCatelogId());
                if (MyObjectUtils.isNotEmpty(categoryInfo)) {
                    infoVo.setCatelogName(categoryInfo.getName());
                }
            }
            return infoVo;
        }).collect(Collectors.toList());
        Page<PmsAttrBaseInfoVo> voPage = new Page<>();
        MyBeanUtils.copyProperties(page, voPage, "records");
        voPage.setRecords(list);
        return new Pagination<PmsAttrBaseInfoVo, PmsAttr>(voPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrDetailVo insertAttr(PmsAttrDetailVo param) {
        PmsAttrAttrgroupRelationVo relationVo = new PmsAttrAttrgroupRelationVo();
        MyBeanUtils.copyProperties(param, relationVo);
        PmsAttr pmsAttr = param.convertToEntity();
        // 保存 pmsAttr
        this.save(pmsAttr);
        relationVo.setAttrId(pmsAttr.getAttrId());
        // 保存 属性与属性分组之间的关系
        attrgroupRelationService.insertAttrAttrRelation(relationVo);
        return param;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrDetailVo updateAttr(PmsAttrDetailVo param) {
        PmsAttrAttrgroupRelation attrgroupRelation = new PmsAttrAttrgroupRelation();
        MyBeanUtils.copyProperties(param, attrgroupRelation);
        PmsAttr pmsAttr = param.convertToEntity();
        this.updateById(pmsAttr);
        PmsAttrAttrgroupRelationVo relation = attrgroupRelationService.getAttrAttrRelationByAttrId(param.getAttrId());
        relation.setAttrGroupId(param.getAttrGroupId());
        attrgroupRelationService.updateById(relation.convertToEntity());
        return param;
    }

    @Override
    public PmsAttrDetailVo getAttrById(Long attrId) {
        PmsAttr pmsAttr = MyCurdUtils.selectOne(this.getById(attrId), ResponseCode.NOT_FOUND);
        PmsAttrDetailVo pmsAttrDetailVo = PmsAttrDetailVo.builder().build().convertToVo(pmsAttr);
        if (MyObjectUtils.isNotEmpty(pmsAttr.getCatelogId())) {
            String[] cateLogPath = pmsCategoryService.findCateLogPath(pmsAttr.getCatelogId());
            pmsAttrDetailVo.setCatelogPath(cateLogPath);
        }
        // 找到属性属于哪个分组
        PmsAttrAttrgroupRelationVo relationVo = attrgroupRelationService.getAttrAttrRelationByAttrId(pmsAttr.getAttrId());
        pmsAttrDetailVo.setAttrGroupId(relationVo.getAttrGroupId());
        return pmsAttrDetailVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteAttr(Long[] ids) {
        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("attr_id", Arrays.asList(ids));
        MyCurdUtils.batchDelete(this.remove(queryWrapper), ResponseCode.DELETE_FAILURE);
        // 删除属性与属性分组之间的关系
        attrgroupRelationService.batchDeleteByAttrId(ids);
    }
}