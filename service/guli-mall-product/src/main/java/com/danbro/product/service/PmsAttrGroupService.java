package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.entity.ResultPageBean;
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
     * @param page       当前页
     * @param limit      每页显示的数
     * @param sidx       排序字段
     * @param order      升序还是降序
     * @param key        关键字
     * @return 分页查询结果
     */
    ResultPageBean getAttrGroupList(Long categoryId, Long page, Long limit, String sidx, String order, String key);
}