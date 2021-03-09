package com.danbro.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsCategory2Vo;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.entity.PmsCategory;

import java.util.List;
import java.util.Map;


/**
 * 商品三级分类(PmsCategory)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
public interface PmsCategoryService extends IService<PmsCategory> {
    /**
     * 获取商品分类的列表（树形结构）
     *
     * @return 商品分类列表
     */
    List<PmsCategoryVo> getCategoryTree();

    /**
     * 通过分类ID批量删除分类（如果有子分类也要删除）
     *
     * @param catIds 分类ID
     */
    void batchDeleteCategoryById(Long[] catIds);

    /**
     * 添加分类
     *
     * @param category 添加的分类对象
     */
    PmsCategoryVo insert(PmsCategoryVo category);

    /**
     * 更新分类
     *
     * @param category 更新的分类对象
     */
    PmsCategoryVo update(PmsCategoryVo category);

    /**
     * 通过分类ID获取分类信息
     *
     * @param categoryId 分类ID
     * @return 信息
     */
    PmsCategoryVo getCategoryInfo(Long categoryId, Boolean throwException);

    /**
     * 批量更新分类
     *
     * @param updateCategoryList 带更新的分类列表
     */
    void batchUpdateCategory(List<PmsCategory> updateCategoryList);

    /**
     * 通过属性分组的三级分类ID查找属性分组的分类路径（父类及祖父的ID）
     *
     * @param cateLogId 属性分组所属的三级分类ID
     * @return 属性分组的分类路径ID数组
     */
    String[] findCateLogPath(Long cateLogId);

    /**
     * 获取指定的分类登记的分类
     *
     * @param catLevel 分类等级
     * @return 分类列表
     */
    List<PmsCategoryVo> getCategoryByCatLevel(Integer catLevel);

    /**
     * 获取返回给前端的分类数据
     *
     * @return
     */
    Map<String, List<PmsCategory2Vo>> getCategoryTreeFroFront();
}