package com.danbro.order.controller.admin;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.order.controller.vo.PmsSpuInfoVo;
import com.danbro.order.entity.PmsSpuInfo;
import com.danbro.order.service.PmsSpuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Api(tags = "spu信息(PmsSpuInfo)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("product/spuinfo")
@Setter
public class PmsSpuInfoController {
    private PmsSpuInfoService pmsSpuInfoService;


    @ApiOperation("添加Spu")
    @PostMapping
    public ResultBean<?> insertSpuInfo(@Validated @RequestBody PmsSpuInfoVo spu) {
        pmsSpuInfoService.insertSpuInfo(spu);
        return ResultBean.ofSuccess();
    }

    @ApiOperation("分页查看Spu")
    @GetMapping("list")
    public ResultPageBean<PmsSpuInfoVo, PmsSpuInfo> getSpuInfoList(@RequestParam("page") Long page,
                                                                   @RequestParam("limit") Long limit,
                                                                   @RequestParam(value = "key", required = false) String key,
                                                                   @RequestParam(value = "brandId", required = false) Long brandId,
                                                                   @RequestParam(value = "catelogId", required = false) Long catelogId,
                                                                   @RequestParam(value = "status", required = false) Integer status) {
        PageParam<PmsSpuInfo> pageParam = new PageParam<PmsSpuInfo>().setLimit(limit).setPage(page);
        return ResultPageBean.ofSuccess(pmsSpuInfoService.queryPageByCondition(pageParam, key, brandId, catelogId, status));
    }

    @ApiOperation("产品上线")
    @PostMapping("{spuId}/up")
    public ResultBean<?> upSpu(@PathVariable Long spuId) {
        pmsSpuInfoService.upSpu(spuId);
        return ResultBean.ofSuccess();
    }

}