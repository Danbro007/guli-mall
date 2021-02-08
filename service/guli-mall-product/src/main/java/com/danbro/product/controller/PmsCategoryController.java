package com.danbro.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.service.PmsCategoryService;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
    public ResultBean<?> deleteCategoryById(@RequestBody Long[] catIds) {
        categoryService.batchDeleteCategoryById(catIds);
        return ResultBean.ofSuccess();
    }

    @ApiOperation("更新分类")
    @PutMapping("")
    public ResultBean<PmsCategoryVo> updateCategory(@Validated(Update.class) @RequestBody PmsCategoryVo categoryParam) {
        return ResultBean.ofSuccess((categoryService.update(categoryParam)));
    }

    @ApiOperation("添加分类")
    @PostMapping("")
    public ResultBean<PmsCategoryVo> insertCategory(@Validated(Insert.class) @RequestBody PmsCategoryVo categoryParam) {
        return ResultBean.ofSuccess(categoryService.insert(categoryParam));
    }

    @ApiOperation("获取分类的详细信息")
    @GetMapping("info/{categoryId}")
    public ResultBean<PmsCategoryVo> getCategoryInfo(@PathVariable Long categoryId) {
        return ResultBean.ofSuccess((categoryService.getCategoryInfo(categoryId)));
    }

    @ApiOperation("更新分类的位置")
    @PutMapping("sort")
    public ResultBean<?> updateCategorySort(@RequestBody PmsCategoryVo[] categoryArrays) {
        List<PmsCategoryVo> categoryParamList = Arrays.asList(categoryArrays);
        categoryService.batchUpdateCategory(categoryParamList.stream().map(PmsCategoryVo::convertToEntity).collect(Collectors.toList()));
        return ResultBean.ofSuccess();
    }
}