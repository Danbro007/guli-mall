package com.danbro.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.pms.OrderType;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.PageUtils;
import com.danbro.product.controller.vo.PmsAttrGroupVo;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.product.entity.PmsCategory;
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
    public ResultPageBean getAttrGroupList(Long categoryId, Long page, Long limit, String sidx, String order, String key) {
        QueryWrapper<PmsAttrGroup> queryWrapper = new QueryWrapper<>();
        // 分类ID
        queryWrapper.eq("catelog_id", categoryId);
        // 升序还是降序，按字段排序
        if (OrderType.ASC.getType().equals(order)) {
            if (MyStrUtils.IsNotEmpty(sidx)) {
                queryWrapper.orderByAsc(sidx);
            } else {
                queryWrapper.orderByAsc("attr_group_name");
            }
        } else {
            if (MyStrUtils.IsNotEmpty(sidx)) {
                queryWrapper.orderByDesc(sidx);
            } else {
                queryWrapper.orderByDesc("attr_group_name");
            }
        }
        // 关键字查询
        if (MyStrUtils.IsNotEmpty(key)) {
            queryWrapper.like("attr_group_name", key);
        }
        PageUtils<PmsAttrGroup> pageUtils = new PageUtils<>(this.page(new Page<>(page, limit), queryWrapper));
        pageUtils.getList().forEach(e->{
            PmsAttrGroupVo vo = new PmsAttrGroupVo().convert(e);

        });
        return ResultPageBean.ofSuccess(new PageUtils(this.page(new Page<>(page, limit), queryWrapper)));
    }
}