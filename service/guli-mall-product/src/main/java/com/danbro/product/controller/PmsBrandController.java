package com.danbro.product.controller;

import java.util.Arrays;

import com.danbro.common.utils.PageUtils;
import com.danbro.product.controller.param.BrandParam;
import com.danbro.product.controller.vo.PmsBrandVo;
import com.danbro.product.service.PmsBrandService;
import com.danbro.service.base.entity.ResultBean;
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
@Api(tags = "品牌(PmsBrand)")
@RestController
@AllArgsConstructor
@RequestMapping("product/brand")
@Setter
public class PmsBrandController {
    private PmsBrandService pmsBrandService;

    @ApiOperation("分页查询品牌")
    @GetMapping("list")
    public ResultBean<PageUtils> getBrandList(@RequestParam Long page,
                                              @RequestParam Long limit,
                                              @RequestParam(required = false) String key) {

        return ResultBean.ofSuccess(pmsBrandService.queryPage(page, limit, key));
    }

    @ApiOperation("修改品牌的显示状态")
    @PutMapping("status")
    public ResultBean<?> updateBrandShowStatus(@Validated(Update.class) @RequestBody BrandParam param) {
        pmsBrandService.insertOrUpdate(param.convertEntity());
        return ResultBean.ofSuccess();
    }

    @ApiOperation("获取品牌的详细信息")
    @GetMapping("info/{brandId}")
    public ResultBean<PmsBrandVo> getBrandInfo(@PathVariable Long brandId) {
        return ResultBean.ofSuccess(new PmsBrandVo().convert(pmsBrandService.getBrandInfoById(brandId)));
    }

    @ApiOperation("批量删除品牌")
    @DeleteMapping("")
    public ResultBean<?> batchDeleteBrand(@RequestBody String[] brandIds) {
        pmsBrandService.removeByIds(Arrays.asList(brandIds));
        return ResultBean.ofSuccess();
    }

    @ApiOperation("修改品牌的信息")
    @PutMapping("")
    public ResultBean<?> updateBrandInfo(@Validated(Update.class) @RequestBody BrandParam param) {
        pmsBrandService.insertOrUpdate(param.convertEntity());

        return ResultBean.ofSuccess();
    }

    @ApiOperation("添加品牌")
    @PostMapping("")
    public ResultBean<?> insertBrandInfo(@Validated(value = Insert.class) @RequestBody BrandParam param) {
        pmsBrandService.insertOrUpdate(param.convertEntity());
        return ResultBean.ofSuccess();
    }
}