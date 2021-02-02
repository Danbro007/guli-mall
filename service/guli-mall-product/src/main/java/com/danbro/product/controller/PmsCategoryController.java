package com.danbro.product.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.service.PmsCategoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Api(tags = "商品三级分类(PmsCategory)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("renren-fast/product/category")
public class PmsCategoryController {

    private PmsCategoryService categoryService;

    //Todo 放缓存里
    @GetMapping("list/tree")
    public ResultBean<List<PmsCategoryVo>> getCategoryTree() {
        return ResultBean.ofSuccess(categoryService.getCategoryTree());
    }

    @DeleteMapping("{categoryId}")
    public ResultBean deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryTreeById(categoryId);
        return ResultBean.ofSuccess();
    }

}