package com.danbro.product.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsAttrAttrgroupRelationVo;
import com.danbro.product.controller.vo.PmsAttrRelationListVo;
import com.danbro.product.entity.PmsAttrAttrgroupRelation;


/**
 * 属性&属性分组关联(PmsAttrAttrgroupRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsAttrAttrgroupRelationService extends IService<PmsAttrAttrgroupRelation> {

    /**
     * 添加属性与属性分组之间的关系数据
     *
     * @param relation 关系数据
     * @return 添加完毕后的关系
     */
    PmsAttrAttrgroupRelationVo insertAttrAttrRelation(PmsAttrAttrgroupRelationVo relation);

    /**
     * 批量添加属性与属性分组之间的关系数据
     *
     * @param relationVoList 多个关系数据
     * @return 添加完毕后的关系
     */
    List<PmsAttrAttrgroupRelationVo> batchInsertAttrAttrRelation(List<PmsAttrAttrgroupRelationVo> relationVoList);


    /**
     * 通过属性ID找到属性与属性分组之间的关系
     *
     * @param attrId         属性ID
     * @param throwException 查找不到是否跑出异常
     * @return 属性与属性分组的关系
     */
    PmsAttrAttrgroupRelationVo getAttrAttrRelationByAttrId(Long attrId, Boolean throwException);

    /**
     * 通过属性数组批量删除
     *
     * @param ids            属性ID数组
     * @param throwException 删除失败是否需要抛出异常
     */
    void batchDeleteByAttrId(Long[] ids, Boolean throwException);

    /**
     * 通过属性分组的数组批量删除
     *
     * @param ids            属性分组的ID数组
     * @param throwException 删除失败是否需要抛出异常
     */
    void batchDeleteByAttrGroupId(Long[] ids, Boolean throwException);

    /**
     * 批量删除属性与属性分组的关系
     * @param paramList 删除条件
     */
    void batchDeleteByAttrIdAndAttrGroupId(List<PmsAttrAttrgroupRelationVo> paramList);
}