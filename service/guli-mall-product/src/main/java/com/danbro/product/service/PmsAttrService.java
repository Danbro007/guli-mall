package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.product.controller.vo.PmsAttrBaseInfoVo;
import com.danbro.product.controller.vo.PmsAttrDetailVo;
import com.danbro.product.controller.vo.PmsProductAttrValueVo;
import com.danbro.product.entity.PmsAttr;

import java.util.List;


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
     * @param attrType   属性类型
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

    /**
     * 通过属性数组批量获取属性
     *
     * @param ids            属性数组
     * @param throwException 找不到的话是否抛出异常
     * @return 查询结果
     */
    List<PmsAttrBaseInfoVo> getListInIds(Long[] ids, Boolean throwException);

    /**
     * 通过属性数组分页批量获取不在属性数组里的属性
     *
     * @param pageParam      分页参数
     * @param ids            排除的属性ID数组
     * @param key            关键字
     * @param throwException 找不到的话是否抛出异常
     * @return 查询结果
     */
    Pagination<PmsAttrBaseInfoVo, PmsAttr> getListNotInIds(PageParam<PmsAttr> pageParam, Long[] ids, String key, Boolean throwException);

    /**
     * 获取Spu的基本属性
     *
     * @param spuId SpuId
     * @return 查询的结果
     */
    List<PmsProductAttrValueVo> getSpuBaseAttrListBySpuId(Long spuId);

    /**
     * 批量更新spu的规格参数
     *
     * @param productAttrValueVoList spu的参数规格数据
     * @param spuId                  spuID
     * @return 更新后的spu规格参数
     */
    List<PmsProductAttrValueVo> batchUpdateSpuBaseAttr(List<PmsProductAttrValueVo> productAttrValueVoList, Long spuId);

    /**
     * 查询出能被检索的属性
     *
     * @param attrIdList 属性ID
     * @return 能被检索的属性
     */
    List<PmsAttrBaseInfoVo> getAttrListWithCanShow(List<Long> attrIdList);
}