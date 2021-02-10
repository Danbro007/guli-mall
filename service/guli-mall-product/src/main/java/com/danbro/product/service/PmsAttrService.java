package com.danbro.product.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.product.controller.vo.PmsAttrBaseInfoVo;
import com.danbro.product.controller.vo.PmsAttrDetailVo;
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
     * @param attrType 属性类型
     * @return 分页查询结果
     */
    Pagination<PmsAttrBaseInfoVo, PmsAttr> attrQueryPage(PageParam<PmsAttr> pageParam, String key, Long categoryId, String attrType);

    /**
     * 添加属性
     *
     * @param param 添加的参数
     * @return 添加完毕后的结果
     */
    PmsAttrDetailVo insertAttr(PmsAttrDetailVo param);


    /**
     * 修改属性
     *
     * @param param 修改的属性
     * @return 修改完毕后的结果
     */
    PmsAttrDetailVo updateAttr(PmsAttrDetailVo param);

    /**
     * 通过属性ID获取属相数据
     *
     * @param attrId 属性参数
     * @return 属性数据
     */
    PmsAttrDetailVo getAttrById(Long attrId);

    /**
     * 批量删除属性同时要到 pmsAttrAttrGroupRelation 表删除
     *
     * @param ids 删除的属性ID列表
     */
    void batchDeleteAttr(Long[] ids);
}