package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.enums.pms.AttrType;
import com.danbro.common.utils.*;
import com.danbro.product.controller.vo.PmsAttrBaseInfoVo;
import com.danbro.product.controller.vo.PmsAttrGroupVo;
import com.danbro.product.entity.PmsAttr;
import com.danbro.product.entity.PmsAttrAttrgroupRelation;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.product.mapper.PmsAttrGroupMapper;
import com.danbro.product.service.PmsAttrAttrgroupRelationService;
import com.danbro.product.service.PmsAttrGroupService;
import com.danbro.product.service.PmsAttrService;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 属性分组(PmsAttrGroup)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsAttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupMapper, PmsAttrGroup> implements PmsAttrGroupService {

    @Autowired
    private PmsCategoryService pmsCategoryService;

    @Autowired
    private PmsAttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    private PmsAttrService pmsAttrService;

    @Override
    public Pagination<PmsAttrGroupVo, PmsAttrGroup> queryPage(PageParam<PmsAttrGroup> param, Long categoryId, String key) {
        // 分类ID 为 0 分页查询所有的属性分组
        LambdaQueryWrapper<PmsAttrGroup> queryWrapper = new LambdaQueryWrapper<>();
        // 有关键字
        if (!MyObjectUtils.isNull(key) && MyStrUtils.isNotEmpty(key)) {
            queryWrapper.eq(PmsAttrGroup::getAttrGroupId, key).or().like(PmsAttrGroup::getAttrGroupName, key);
        }
        // 分类ID > 0
        if (categoryId > 0) {
            queryWrapper.eq(PmsAttrGroup::getCatelogId, categoryId);
        }
        return new Pagination<>(this.page(new Query<PmsAttrGroup>().getPage(param), queryWrapper), PmsAttrGroupVo.class);

    }

    @Override
    public PmsAttrGroupVo insertOrUpdate(PmsAttrGroupVo attrGroup) {
        PmsAttrGroup pmsAttrGroup = attrGroup.convertToEntity();
        boolean result = this.saveOrUpdate(pmsAttrGroup);
        return MyCurdUtils.insertOrUpdate(attrGroup.convertToVo(pmsAttrGroup), result, ResponseCode.INSERT_FAILURE);
    }

    @Override
    public PmsAttrGroupVo getAttrGroupInfo(Long attrGroupId, Boolean throwException) {
        PmsAttrGroup pmsAttrGroup = MyCurdUtils.select(this.getById(attrGroupId), ResponseCode.NOT_FOUND, throwException);
        if (MyObjectUtils.isNotNull(pmsAttrGroup)) {
            PmsAttrGroupVo attrGroupVo = PmsAttrGroupVo.builder().build().convertToVo(pmsAttrGroup);
            String[] cateLogPath = pmsCategoryService.findCateLogPath(attrGroupVo.getCatelogId());
            attrGroupVo.setCatelogPath(cateLogPath);
            return attrGroupVo;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteAttrGroup(Long[] ids) {
        // 到 attr-attr-group-relation 表删除数据
        attrAttrgroupRelationService.batchDeleteByAttrGroupId(ids, true);
        // 到 attrGroup 表删除数据
        MyCurdUtils.delete(this.removeByIds(Arrays.asList(ids)), ResponseCode.DELETE_FAILURE);
    }

    @Override
    public List<PmsAttrBaseInfoVo> getAttrListByAttrGroupId(Long attrGroupId, Boolean throwException) {
        // 先到关系表查询到属性分组下面的所有属性ID，找不到则返回null
        List<PmsAttrAttrgroupRelation> relations = MyCurdUtils.select(attrAttrgroupRelationService.list(new QueryWrapper<PmsAttrAttrgroupRelation>().lambda().
                        eq(PmsAttrAttrgroupRelation::getAttrGroupId, attrGroupId)),
                ResponseCode.NOT_FOUND,
                false);
        if (MyCollectionUtils.isEmpty(relations)) {
            return null;
        }
        // 通过ID数组找到属性信息
        return pmsAttrService.getListInIds(relations.stream().map(PmsAttrAttrgroupRelation::getAttrId).toArray(Long[]::new), true);
    }

    @Override
    public Pagination<PmsAttrBaseInfoVo, PmsAttr> getNoAttrListByAttrGroupId(PageParam<PmsAttr> param, Long attrGroupId, String key, Boolean throwException) {
        // 1、查找当前属性分组的分类ID
        PmsAttrGroup pmsAttrGroup = MyCurdUtils.select(this.getById(attrGroupId), ResponseCode.NOT_FOUND);
        // 2、查找当前分类ID下其他的属性分组（不包括当前的属性分组）
        List<PmsAttrGroup> pmsAttrGroupList = MyCurdUtils.selectList(this.list(new QueryWrapper<PmsAttrGroup>()
                .lambda()
                .eq(PmsAttrGroup::getCatelogId, pmsAttrGroup.getCatelogId())
                .ne(PmsAttrGroup::getAttrGroupId, attrGroupId)), ResponseCode.NOT_FOUND, throwException);
        // 3、再查找这些属性分组下的所有属性
        List<PmsAttrAttrgroupRelation> relations = attrAttrgroupRelationService.list(new QueryWrapper<PmsAttrAttrgroupRelation>().
                lambda().
                in(PmsAttrAttrgroupRelation::getAttrGroupId,
                        pmsAttrGroupList.stream().map(PmsAttrGroup::getAttrGroupId).collect(Collectors.toList())
                ));
        List<Long> attrIdList = relations.stream().map(PmsAttrAttrgroupRelation::getAttrId).collect(Collectors.toList());
        // 4、再通过排除这些已被当前分类属性组分配的属性，找到还没有被当前分组分配的属性
        return new Pagination<>(pmsAttrService.page(new Query<PmsAttr>().getPage(param),
                new QueryWrapper<PmsAttr>().lambda().
                        eq(PmsAttr::getCatelogId, pmsAttrGroup.getCatelogId()).
                        notIn(PmsAttr::getAttrId, attrIdList)),
                PmsAttrBaseInfoVo.class);
    }

    @Override
    public List<PmsAttrGroupVo> getAttrGroupAndAttrByCatId(Long catId) {
        // 1、先查找出该分类下的属性分组的ID
        List<PmsAttrGroup> attrGroupList = MyCurdUtils.selectList(this.list(new QueryWrapper<PmsAttrGroup>().lambda().eq(PmsAttrGroup::getCatelogId, catId)), ResponseCode.NOT_FOUND);
        // 2、通过属性分组查找出下属的属性ID
        List<PmsAttrAttrgroupRelation> relations = attrAttrgroupRelationService.
                list(new QueryWrapper<PmsAttrAttrgroupRelation>()
                        .lambda()
                        .in(PmsAttrAttrgroupRelation::getAttrGroupId,
                                attrGroupList.stream().map(PmsAttrGroup::getAttrGroupId).
                                        collect(Collectors.toList())));
        MyCurdUtils.select(relations, ResponseCode.NOT_FOUND);
        // 3、通过属性ID找到所有的属性信息
        return attrGroupList.stream().map(attrGroup -> {
            PmsAttrGroupVo pmsAttrGroupVo = PmsAttrGroupVo.builder().build().convertToVo(attrGroup);
            // 3.1 找到当前属性分组的下属属性ID
            List<PmsAttrAttrgroupRelation> relationList = relations.stream().filter(relation -> relation.getAttrGroupId().equals(attrGroup.getAttrGroupId())).collect(Collectors.toList());
            if (MyCollectionUtils.isNotEmpty(relationList)) {
                // 3.2 通过属性ID找到规格属性对象
                LambdaQueryWrapper<PmsAttr> queryWrapper = new QueryWrapper<PmsAttr>().lambda().in(PmsAttr::getAttrId, relationList.stream().map(PmsAttrAttrgroupRelation::getAttrId).collect(Collectors.toList())).eq(PmsAttr::getValueType, AttrType.BASE.getCode());
                List<PmsAttr> pmsAttrList = pmsAttrService.list(queryWrapper);
                if (MyCollectionUtils.isNotEmpty(pmsAttrList)) {
                    // 3.3 把属性转换成Vo对象并封装到属性分组里
                    List<PmsAttrBaseInfoVo> attrBaseInfoVoList = pmsAttrList.stream().map(n -> PmsAttrBaseInfoVo.builder().build().convertToVo(n)).collect(Collectors.toList());
                    pmsAttrGroupVo.setAttrs(attrBaseInfoVoList);
                }
            }
            return pmsAttrGroupVo;
        }).collect(Collectors.toList());
    }
}