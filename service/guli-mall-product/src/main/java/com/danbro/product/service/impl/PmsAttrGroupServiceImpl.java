package com.danbro.product.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.common.enums.pms.OrderType;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyStrUtils;
import com.danbro.common.utils.PageUtils;
import com.danbro.common.utils.Query;
import com.danbro.product.controller.vo.PmsAttrGroupVo;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.product.mapper.PmsAttrGroupMapper;
import com.danbro.product.service.PmsAttrGroupService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        if (categoryId == 0) {
            page = this.page(new Query<PmsAttrGroup>().getPage(param));
        } else {
            // 有分类ID
            QueryWrapper<PmsAttrGroup> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("catelog_id", categoryId);
            // 看有没有关键字
            if (!MyObjectUtils.isEmpty(key) && MyStrUtils.isNotEmpty(key)) {
                queryWrapper.eq("attr_group_id", key).or().like("attr_group_name", key);
            }
            page = this.page(new Query<PmsAttrGroup>().getPage(param), queryWrapper);
        }
        return ResultPageBean.ofSuccess(new PageUtils<>(page));
    }
}