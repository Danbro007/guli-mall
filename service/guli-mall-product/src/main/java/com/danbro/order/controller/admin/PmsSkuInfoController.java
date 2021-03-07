package com.danbro.order.controller.admin;

import java.math.BigDecimal;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.order.controller.vo.PmsSkuInfoVo;
import com.danbro.order.entity.PmsSkuInfo;
import com.danbro.order.service.PmsSkuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Api(tags = "sku信息(PmsSkuInfo)")
@Validated
@RestController
@AllArgsConstructor
@Setter
@RequestMapping("product/skuinfo")
public class PmsSkuInfoController {
    private PmsSkuInfoService pmsSkuInfoService;

    @ApiOperation("分页查询sku")
    @GetMapping("list")
    public ResultPageBean<PmsSkuInfoVo, PmsSkuInfo> getSkuInfoList(@RequestParam("page") Long page,
                                                                   @RequestParam("limit") Long limit,
                                                                   @RequestParam(value = "key", required = false) String key,
                                                                   @RequestParam(value = "brandId", required = false) Long brandId,
                                                                   @RequestParam(value = "catelogId", required = false) Long catelogId,
                                                                   @RequestParam(value = "min", required = false) BigDecimal min,
                                                                   @RequestParam(value = "max", required = false) BigDecimal max) {
        PageParam<PmsSkuInfo> pageParam = new PageParam<PmsSkuInfo>().setPage(page).setLimit(limit);
        return ResultPageBean.ofSuccess(pmsSkuInfoService.queryPageByCondition(pageParam, key, brandId, catelogId, min, max));
    }

    @ApiOperation("查询sku的详细信息")
    @GetMapping("info/{skuId}")
    public ResultBean<PmsSkuInfoVo> getSkuInfo(@PathVariable Long skuId) {
        return ResultBean.ofSuccess(pmsSkuInfoService.getSkuInfoById(skuId));
    }
}