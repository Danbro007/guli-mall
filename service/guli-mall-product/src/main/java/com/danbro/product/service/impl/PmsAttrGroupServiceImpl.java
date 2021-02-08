package com.danbro.product.service.impl;

import java.util.Arrays;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.*;
import com.danbro.product.controller.vo.PmsAttrGroupVo;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.product.mapper.PmsAttrGroupMapper;
import com.danbro.product.service.PmsAttrGroupService;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Pagination<PmsAttrGroupVo,PmsAttrGroup> queryPage(PageParam<PmsAttrGroup> param, Long categoryId, String key) {
        // 分类ID 为 0 分页查询所有的属性分组
        IPage<PmsAttrGroup> page;
        QueryWrapper<PmsAttrGroup> queryWrapper = new QueryWrapper<>();
        // 有关键字
        if (!MyObjectUtils.isEmpty(key) && MyStrUtils.isNotEmpty(key)) {
            queryWrapper.eq("attr_group_id", key).or().like("attr_group_name", key);
        }
        // 分类ID > 0
        if (categoryId > 0) {
            queryWrapper.eq("catelog_id", categoryId);
        }
        return new Pagination<>(this.page(new Query<PmsAttrGroup>().getPage(param), queryWrapper),PmsAttrGroupVo.class);

    }

    @Override
    public PmsAttrGroupVo insertOrUpdate(PmsAttrGroupVo attrGroup) {
        return MyCurdUtils.insertOrUpdate(attrGroup, this.saveOrUpdate(attrGroup.convertToEntity()), ResponseCode.INSERT_FAILURE);
    }

    @Override
    public PmsAttrGroupVo getAttrGroupInfo(Long attrGroupId) {
        PmsAttrGroup pmsAttrGroup = MyCurdUtils.selectOne(this.getById(attrGroupId), ResponseCode.NOT_FOUND);
        PmsAttrGroupVo attrGroupVo = PmsAttrGroupVo.builder().build().convertToVo(pmsAttrGroup);
        String[] cateLogPath = pmsCategoryService.findCateLogPath(attrGroupVo.getCatelogId());
        attrGroupVo.setCatelogPath(cateLogPath);
        return attrGroupVo;
    }

    @Override
    public void batchDeleteAttrGroup(Long[] ids) {
        MyCurdUtils.delete(this.removeByIds(Arrays.asList(ids)), ResponseCode.DELETE_FAILURE);
    }
}