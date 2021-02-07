package com.danbro.product.controller;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.param.CategoryBrandRelationParam;
import com.danbro.product.controller.vo.PmsCategoryBrandRelationVo;
import com.danbro.product.service.PmsCategoryBrandRelationService;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Api(tags = "品牌分类关联(PmsCategoryBrandRelation)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("product/categorybrandrelation")
@Setter
public class PmsCategoryBrandRelationController {
    private PmsCategoryBrandRelationService pmsCategoryBrandRelationService;

    @PostMapping("")
    public ResultBean<?> insertCategoryBrandRelation(@Validated(Insert.class) @RequestBody CategoryBrandRelationParam param) {
        return ResultBean.ofSuccess(PmsCategoryBrandRelationVo.builder().build().convert(pmsCategoryBrandRelationService.insertOrUpdateCategoryBrandRelation(param.convertEntity())));
    }

    @PutMapping("")
    public ResultBean<?> updateCategoryBrandRelation(@Validated(Update.class) @RequestBody CategoryBrandRelationParam param) {
        return ResultBean.ofSuccess(PmsCategoryBrandRelationVo.builder().build().convert(pmsCategoryBrandRelationService.insertOrUpdateCategoryBrandRelation(param.convertEntity())));
    }

    @GetMapping("catelog/list")
    public ResultBean<List<PmsCategoryBrandRelationVo>> getBrandCategoryList(@RequestParam Long brandId) {
        return ResultBean.ofSuccess(pmsCategoryBrandRelationService.getBrandRelationList(brandId));
    }

    @DeleteMapping("")
    public ResultBean<?> deleteCategoryBrandRelation(@RequestBody Long[] ids) {
        pmsCategoryBrandRelationService.batchDeleteCategoryBrandRelation(ids);
        return ResultBean.ofSuccess();
    }

}