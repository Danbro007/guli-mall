package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.product.entity.PmsAttrGroup;


/**
 * 属性分组(PmsAttrGroup)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsAttrGroupService extends IService<PmsAttrGroup> {
    /**
     * 分页查询属性组
     *
     * @param categoryId 三级分类ID
     * @param pageParam  分页参数
     * @param key        关键字
     * @return 分页查询结果
     */
    ResultPageBean getAttrGroupList(PageParam<PmsAttrGroup> pageParam, Long categoryId, String key);

    /**
     * 添加或者更新属性分组
     *
     * @param attrGroup 属性分组对象
     * @return 添加或者更新完毕后的属性分组对象
     */
    PmsAttrGroup insertOrUpdate(PmsAttrGroup attrGroup);
}