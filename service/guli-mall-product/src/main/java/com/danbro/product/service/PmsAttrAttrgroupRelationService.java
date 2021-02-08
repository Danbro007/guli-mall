package com.danbro.product.service;
 
 
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsAttrAttrgroupRelationVo;
import com.danbro.product.entity.PmsAttrAttrgroupRelation;


/**
 * 属性&属性分组关联(PmsAttrAttrgroupRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsAttrAttrgroupRelationService extends IService<PmsAttrAttrgroupRelation> {

    /**
     * 添加属性与属性分组之间的关系
     * @param attrgroupRelation 关系数据
     * @return 添加完毕后的关系
     */
    PmsAttrAttrgroupRelationVo insertAttrAttrRelation(PmsAttrAttrgroupRelationVo attrgroupRelation);

    /**
     * 通过属性ID找到属性与属性分组之间的关系
     * @param attrId 属性ID
     * @return 属性与属性分组的关系
     */
    PmsAttrAttrgroupRelationVo getAttrAttrRelationByAttrId(Long attrId);

    /**
     * 通过属性数组批量删除
     * @param ids 属性ID数组
     */
    void batchDeleteByAttrId(Long ids[]);
    /**
     * 通过属性分组的数组批量删除
     * @param ids 属性分组的ID数组
     */
    void batchDeleteByAttrGroupId(Long ids[]);

}