package com.danbro.product.service;


import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.entity.PmsCategory;


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
     * 通过分类ID删除分类（如果有子分类也要删除）
     *
     * @param categoryId 分类ID
     */
    void deleteCategoryTreeById(String categoryId);
}