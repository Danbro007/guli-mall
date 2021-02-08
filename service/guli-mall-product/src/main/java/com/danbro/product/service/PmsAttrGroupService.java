package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.product.controller.vo.PmsAttrGroupVo;
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
    Pagination<PmsAttrGroupVo,PmsAttrGroup> getAttrGroupList(PageParam<PmsAttrGroup> pageParam, Long categoryId, String key);

    /**
     * 添加或者更新属性分组
     *
     * @param attrGroup 属性分组对象
     * @return 添加或者更新完毕后的属性分组对象
     */
    PmsAttrGroup insertOrUpdate(PmsAttrGroup attrGroup);

    /**
     * 获取详细的属性分组信息
     *
     * @param attrGroupId 属性分组ID
     * @return 属性分组信息
     */
    PmsAttrGroupVo getAttrGroupInfo(Long attrGroupId);

    /**
     * 删除指定的属性分组
     * @param ids 属性分组的ID数组
     */
    void batchDeleteAttrGroup(Long[] ids);
}