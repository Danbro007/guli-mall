package com.danbro.order.service;


import java.util.List;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.order.controller.vo.PmsAttrBaseInfoVo;
import com.danbro.order.controller.vo.PmsAttrGroupVo;
import com.danbro.order.entity.PmsAttr;
import com.danbro.order.entity.PmsAttrGroup;


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
    Pagination<PmsAttrGroupVo, PmsAttrGroup> queryPage(PageParam<PmsAttrGroup> pageParam, Long categoryId, String key);

    /**
     * 添加或者更新属性分组
     *
     * @param attrGroup 属性分组对象
     * @return 添加或者更新完毕后的属性分组对象
     */
    PmsAttrGroupVo insertOrUpdate(PmsAttrGroupVo attrGroup);

    /**
     * 获取详细的属性分组信息
     *
     * @param attrGroupId    属性分组ID
     * @param throwException 查找失败是否抛出异常
     * @return 属性分组信息
     */
    PmsAttrGroupVo getAttrGroupInfo(Long attrGroupId, Boolean throwException);

    /**
     * 删除指定的属性分组
     *
     * @param ids 属性分组的ID数组
     */
    void batchDeleteAttrGroup(Long[] ids);

    /**
     * 通过属性分组 ID 查找属性分组下所有属性
     *
     * @param attrGroupId    属性分组ID
     * @param throwException 找不到是否抛出异常
     * @return 查找到的属性列表
     */
    List<PmsAttrBaseInfoVo> getAttrListByAttrGroupId(Long attrGroupId, Boolean throwException);

    /**
     * 通过属性分组 ID 查分页查找不在属性分组下所有属性
     *
     * @param pageParam      属性分组分页参数
     * @param attrGroupId    属性分组ID
     * @param throwException 找不到是否抛出异常
     * @param key            关键字
     * @return 查找到的属性列表
     */
    Pagination<PmsAttrBaseInfoVo, PmsAttr> getNoAttrListByAttrGroupId(PageParam<PmsAttr> pageParam, Long attrGroupId, String key, Boolean throwException);


    /**
     * 查找该分类Id下的所有属性分组既下面的属性
     *
     * @param catId 分类ID
     * @return 查找到的属性分组列表
     */
    List<PmsAttrGroupVo> getAttrGroupAndAttrByCatId(Long catId);
}