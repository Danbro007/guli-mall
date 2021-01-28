package com.danbro.product.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.entity.PmsCategory;
import com.danbro.product.mapper.PmsCategoryMapper;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.stereotype.Service;

/**
 * 商品三级分类(PmsCategory)表服务实现类
 *
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Service
public class PmsCategoryServiceImpl extends ServiceImpl<PmsCategoryMapper, PmsCategory> implements PmsCategoryService {

    @Override
    public List<PmsCategoryVo> getCategoryTree() {
        // 先找所有的一级分类
        List<PmsCategory> allPmsCategory = this.list();
        return allPmsCategory.stream().filter(e -> 0 == e.getParentCid()).map(m -> {
            PmsCategoryVo categoryVo = new PmsCategoryVo().convert(m);
            categoryVo.setChildren(getChildren(categoryVo, allPmsCategory));
            return categoryVo;
        }).sorted(Comparator.comparingInt(PmsCategoryVo::getSort)).collect(Collectors.toList());
    }

    /**
     * 获取父分类的子分类
     *
     * @param root        父分类
     * @param allCategory 所有分类列表
     * @return 子分类
     */
    private List<PmsCategoryVo> getChildren(PmsCategoryVo root, List<PmsCategory> allCategory) {
        return allCategory.stream().filter(e -> e.getParentCid().equals(root.getCatId())).map(m -> {
            PmsCategoryVo categoryVo = new PmsCategoryVo().convert(m);
            categoryVo.setChildren(getChildren(categoryVo, allCategory));
            return categoryVo;
        }).sorted(Comparator.comparingInt(PmsCategoryVo::getSort)).collect(Collectors.toList());

    }
}