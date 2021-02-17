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
import com.danbro.common.enums.pms.AttrType;
import com.danbro.common.utils.*;
import com.danbro.product.controller.vo.*;
import com.danbro.product.entity.PmsAttr;
import com.danbro.product.entity.PmsAttrAttrgroupRelation;
import com.danbro.product.entity.PmsProductAttrValue;
import com.danbro.product.mapper.PmsAttrMapper;
import com.danbro.product.service.PmsAttrAttrgroupRelationService;
import com.danbro.product.service.PmsAttrGroupService;
import com.danbro.product.service.PmsAttrService;
import com.danbro.product.service.PmsCategoryService;
import com.danbro.product.service.PmsProductAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PmsCategoryService pmsCategoryService;

    @Autowired
    private PmsAttrGroupService pmsAttrGroupService;

    @Autowired
    PmsAttrAttrgroupRelationService attrgroupRelationService;

    @Autowired
    PmsProductAttrValueService pmsProductAttrValueService;

    @Override
    public Pagination<PmsAttrBaseInfoVo, PmsAttr> attrQueryPage(PageParam<PmsAttr> pageParam, String key, Long categoryId, String attrType) {
        IPage<PmsAttr> page;
        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<>();
        // 返回所有
        if (MyObjectUtils.isNotNull(categoryId) && categoryId > 0) {
            queryWrapper.eq("catelog_id", categoryId);
        }
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like("attr_name", key).or().like("attr_id", key);
        }
        // 查询的属性类型
        queryWrapper.eq("attr_type", AttrType.BASE.getType().equalsIgnoreCase(attrType) ? AttrType.BASE.getCode() : AttrType.SALE.getCode());
        //分页查找
        page = this.page(new Query<PmsAttr>().getPage(pageParam), queryWrapper);
        // 遍历每一个属性对象然后查找属性对应的属性分组的组名和与分类名。
        List<PmsAttrBaseInfoVo> list = page.getRecords().stream().map(attr -> {
            PmsAttrBaseInfoVo infoVo = PmsAttrBaseInfoVo.builder().build().convertToVo(attr);
            // 找到属性对应的属性分组
            if (MyObjectUtils.isNotNull(attr.getAttrId())) {
                PmsAttrAttrgroupRelationVo relation = attrgroupRelationService.getAttrAttrRelationByAttrId(infoVo.getAttrId(), false);
                if (MyObjectUtils.isNotNull(relation)) {
                    PmsAttrGroupVo groupInfo = pmsAttrGroupService.getAttrGroupInfo(relation.getAttrGroupId(), false);
                    if (MyObjectUtils.isNotNull(groupInfo)) {
                        infoVo.setGroupName(groupInfo.getAttrGroupName());
                    }
                }
            }
            // 再查找属性对应的分类
            if (MyObjectUtils.isNotNull(attr.getCatelogId())) {
                PmsCategoryVo categoryInfo = pmsCategoryService.getCategoryInfo(attr.getCatelogId(), false);
                if (MyObjectUtils.isNotNull(categoryInfo)) {
                    infoVo.setCatelogName(categoryInfo.getName());
                }
            }
            return infoVo;
        }).collect(Collectors.toList());
        Page<PmsAttrBaseInfoVo> voPage = new Page<>();
        MyBeanUtils.copyProperties(page, voPage, "records");
        voPage.setRecords(list);
        return new Pagination<>(voPage);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrDetailVo insertAttr(PmsAttrDetailVo param) {
        PmsAttrAttrgroupRelationVo relationVo = new PmsAttrAttrgroupRelationVo();
        MyBeanUtils.copyProperties(param, relationVo);
        PmsAttr pmsAttr = param.convertToEntity();
        // 销售属性不用保存分组关系
        if (AttrType.BASE.getCode().equals(pmsAttr.getAttrType())) {
            // 保存 pmsAttr
            this.save(pmsAttr);
            relationVo.setAttrId(pmsAttr.getAttrId());
            // 保存属性与属性分组之间的关系
            attrgroupRelationService.insertAttrAttrRelation(relationVo);
        }
        return param;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PmsAttrDetailVo updateAttr(PmsAttrDetailVo param) {
        PmsAttrAttrgroupRelation attrgroupRelation = new PmsAttrAttrgroupRelation();
        MyBeanUtils.copyProperties(param, attrgroupRelation);
        PmsAttr pmsAttr = param.convertToEntity();
        this.updateById(pmsAttr);
        // 销售属性不用更新分组关系
        if (AttrType.BASE.getCode().equals(pmsAttr.getAttrType())) {
            attrgroupRelationService.remove(new QueryWrapper<PmsAttrAttrgroupRelation>().lambda().
                    eq(PmsAttrAttrgroupRelation::getAttrId, param.getAttrId()).eq(PmsAttrAttrgroupRelation::getAttrGroupId, param.getAttrGroupId()));
            // 到 attr_attr_group_relation 表里更新数据
            PmsAttrAttrgroupRelationVo attrgroupRelationVo = new PmsAttrAttrgroupRelationVo().setAttrGroupId(param.getAttrGroupId()).setAttrId(param.getAttrId());
            attrgroupRelationService.insertAttrAttrRelation(attrgroupRelationVo);
        }
        return param;
    }

    @Override
    public PmsAttrDetailVo getAttrById(Long attrId) {
        PmsAttr pmsAttr = MyCurdUtils.select(this.getById(attrId), ResponseCode.NOT_FOUND);
        PmsAttrDetailVo pmsAttrDetailVo = PmsAttrDetailVo.builder().build().convertToVo(pmsAttr);
        if (MyObjectUtils.isNotNull(pmsAttr.getCatelogId())) {
            // 查询属性属于哪一个分类
            String[] cateLogPath = pmsCategoryService.findCateLogPath(pmsAttr.getCatelogId());
            pmsAttrDetailVo.setCatelogPath(cateLogPath);
        }
        // 如果是销售属性则不用查属性与属性分组的关系
        if (AttrType.BASE.getCode().equals(pmsAttr.getAttrType())) {
            // 找到属性属于哪个分组
            PmsAttrAttrgroupRelationVo relationVo = attrgroupRelationService.getAttrAttrRelationByAttrId(pmsAttr.getAttrId(), false);
            if (MyObjectUtils.isNotNull(relationVo)) {
                pmsAttrDetailVo.setAttrGroupId(relationVo.getAttrGroupId());
            }
        }
        return pmsAttrDetailVo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteAttr(Long[] ids) {
        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("attr_id", Arrays.asList(ids));
        // 如果是销售属性则不用到 pms_attr_attr_group_relation 表删除，如果是基本属性需要删除pms_attr_attr_group_relation 表的关系
        List<PmsAttr> baseAttrList = this.list(queryWrapper).stream().filter(attr -> AttrType.BASE.getCode().equals(attr.getAttrType())).collect(Collectors.toList());
        // 先到 pms_attr 里删除
        MyCurdUtils.batchDelete(this.remove(queryWrapper), ResponseCode.DELETE_FAILURE);
        // 找到要删除的基本属性的ID
        if (MyCollectionUtils.isEmpty(baseAttrList)) {
            return;
        }
        List<Long> deleteList = baseAttrList.stream().map(PmsAttr::getAttrId).collect(Collectors.toList());
        Long[] idArray = new Long[deleteList.size()];
        // 到 pms_attr_attr_group_relation 里删除
        attrgroupRelationService.batchDeleteByAttrId(deleteList.toArray(idArray), true);
    }

    @Override
    public List<PmsAttrBaseInfoVo> getListInIds(Long[] ids, Boolean throwException) {
        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("attr_id", Arrays.asList(ids));
        List<PmsAttr> pmsAttrs = MyCurdUtils.select(this.list(queryWrapper), ResponseCode.NOT_FOUND, throwException);
        return pmsAttrs.stream().map(attr -> PmsAttrBaseInfoVo.builder().build().convertToVo(attr)).collect(Collectors.toList());
    }

    @Override
    public Pagination<PmsAttrBaseInfoVo, PmsAttr> getListNotInIds(PageParam<PmsAttr> pageParam, Long[] ids, String key, Boolean throwException) {
        QueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.notIn("attr_id", Arrays.asList(ids));
        if (MyStrUtils.isNotEmpty(key)) {
            queryWrapper.like("attr_id", key).or().like("attr_name", key);
        }
        return new Pagination<>(this.page(new Query<PmsAttr>().getPage(pageParam), queryWrapper), PmsAttrBaseInfoVo.class);
    }

    @Override
    public List<PmsProductAttrValueVo> getSpuBaseAttrListBySpuId(Long spuId) {
        List<PmsProductAttrValue> productAttrValueList = MyCurdUtils.select(pmsProductAttrValueService.list(new QueryWrapper<PmsProductAttrValue>().lambda().eq(PmsProductAttrValue::getSpuId, spuId)), ResponseCode.NOT_FOUND);
        return productAttrValueList.stream().map(attr -> PmsProductAttrValueVo.builder().build().convertToVo(attr)).collect(Collectors.toList());
    }

    @Override
    public List<PmsProductAttrValueVo> batchUpdateSpuBaseAttr(List<PmsProductAttrValueVo> productAttrValueVoList, Long spuId) {
        // 先更新能查询到的，然后添加原来没有的
        List<PmsProductAttrValue> productAttrValues = productAttrValueVoList.stream().map(PmsProductAttrValueVo::convertToEntity).collect(Collectors.toList());
        productAttrValues.forEach(attr -> attr.setSpuId(spuId));
        boolean result = pmsProductAttrValueService.saveOrUpdateBatch(productAttrValues);
        return MyCurdUtils.batchInsertOrUpdate(productAttrValues.stream().map(attr -> PmsProductAttrValueVo.builder().build().convertToVo(attr)).collect(Collectors.toList()),
                result,
                ResponseCode.UPDATE_FAILURE);
    }

    @Override
    public List<PmsAttrBaseInfoVo> getBaseAttrListWithCanShow(List<Long> attrIdList) {
        List<PmsAttr> pmsAttrList = MyCurdUtils.selectList(this.list(new QueryWrapper<PmsAttr>().lambda().
                        in(PmsAttr::getAttrId, attrIdList).
                        eq(PmsAttr::getSearchType, true)),
                ResponseCode.NOT_FOUND);
        return pmsAttrList.stream().map(attr -> PmsAttrBaseInfoVo.builder().build().convertToVo(attr)).collect(Collectors.toList());
    }
}