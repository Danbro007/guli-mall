package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.*;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.product.mapper.PmsAttrGroupMapper;
import com.danbro.product.service.PmsAttrGroupService;
import org.springframework.stereotype.Service;

/**
 * 属性分组(PmsAttrGroup)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsAttrGroupServiceImpl extends ServiceImpl<PmsAttrGroupMapper, PmsAttrGroup> implements PmsAttrGroupService {

    @Override
    public ResultPageBean getAttrGroupList(PageParam<PmsAttrGroup> param, Long categoryId, String key) {
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
        page = this.page(new Query<PmsAttrGroup>().getPage(param), queryWrapper);
        return ResultPageBean.ofSuccess(new PageUtils<>(page));
    }

    @Override
    public PmsAttrGroup insertOrUpdate(PmsAttrGroup attrGroup) {
        return MyCurdUtils.insertOrUpdate(attrGroup, this.saveOrUpdate(attrGroup), ResponseCode.INSERT_OR_UPDATE_FAILURE);
    }
}