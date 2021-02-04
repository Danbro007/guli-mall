package com.danbro.product.controller;

import com.danbro.product.controller.param.CategoryParam;
import com.danbro.product.controller.vo.PmsCategoryInfoVo;
import com.danbro.service.base.entity.ResultBean;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.service.PmsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Api(tags = "商品三级分类维护(PmsCategory)")
@RestController
@AllArgsConstructor
@RequestMapping("product/category")
@Setter
public class PmsCategoryController {

    private PmsCategoryService categoryService;

    @ApiOperation("获取所有分类（树形结构）")
    @GetMapping("list/tree")
    public ResultBean<List<PmsCategoryVo>> getCategoryTree() {

        return ResultBean.ofSuccess(categoryService.getCategoryTree());
    }

    @ApiOperation("批量删除分类")
    @DeleteMapping("")
    public ResultBean<?> deleteCategoryById(@RequestBody String[] catIds) {
        categoryService.batchDeleteCategoryById(catIds);
        return ResultBean.ofSuccess();
    }

    @ApiOperation("更新分类")
    @PutMapping("")
    public ResultBean<?> updateCategory(@RequestBody CategoryParam categoryParam) {
        categoryService.insertOrUpdateCategory(categoryParam.convertEntity());
        return ResultBean.ofSuccess();
    }

    @ApiOperation("添加分类")
    @PostMapping("")
    public ResultBean<?> insertCategory(@RequestBody CategoryParam categoryParam) {
        categoryService.insertOrUpdateCategory(categoryParam.convertEntity());
        return ResultBean.ofSuccess();
    }

    @ApiOperation("获取分类的详细信息")
    @GetMapping("info/{categoryId}")
    public ResultBean<PmsCategoryInfoVo> getCategoryInfo(@PathVariable Long categoryId) {
        return ResultBean.ofSuccess(new PmsCategoryInfoVo().convert(categoryService.getCategoryInfo(categoryId)));
    }

    @ApiOperation("更新分类的位置")
    @PutMapping("sort")
    public ResultBean<?> updateCategorySort(@RequestBody CategoryParam[] categoryArrays) {
        List<CategoryParam> categoryParamList = Arrays.asList(categoryArrays);
        categoryService.batchUpdateCategory(categoryParamList.stream().map(CategoryParam::convertEntity).collect(Collectors.toList()));
        return ResultBean.ofSuccess();
    }
}