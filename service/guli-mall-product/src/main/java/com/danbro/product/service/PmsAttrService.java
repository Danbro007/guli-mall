package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.product.controller.vo.PmsAttrVo;
import com.danbro.product.entity.PmsAttr;


/**
 * 商品属性(PmsAttr)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsAttrService extends IService<PmsAttr> {
    /**
     * 分页查询属性
     *
     * @param pageParam  分页参数
     * @param key        关键字
     * @param categoryId 三级分类ID
     * @return 分页查询结果
     */
    Pagination<PmsAttrVo, PmsAttr> queryPage(PageParam<PmsAttr> pageParam, String key, Long categoryId);

    /**
     * 添加属性
     *
     * @param param 添加的参数
     * @return 添加完毕后的结果
     */
    PmsAttrVo insertAttr(PmsAttrVo param);


    /**
     * 修改属性
     *
     * @param param 修改的属性
     * @return 修改完毕后的结果
     */
    PmsAttrVo updateAttr(PmsAttrVo param);

    /**
     * 通过属性ID获取属相数据
     * @param attrId 属性参数
     * @return 属性数据
     */
    PmsAttrVo getAttrById(Long attrId);
}