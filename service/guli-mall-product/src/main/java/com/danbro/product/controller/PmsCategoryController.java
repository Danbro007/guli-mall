package com.danbro.product.controller;

import java.util.List;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.service.PmsCategoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Api(tags = "商品三级分类(PmsCategory)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("product/category")
public class PmsCategoryController {
    @Autowired
    private PmsCategoryService categoryService;

    //Todo 放缓存里
    @GetMapping("list/tree")
    public List<PmsCategoryVo> getCategoryTree() {
        return categoryService.getCategoryTree();
    }

}