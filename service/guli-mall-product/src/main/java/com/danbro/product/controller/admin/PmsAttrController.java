package com.danbro.product.controller.admin;

import java.util.List;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.product.controller.vo.PmsAttrBaseInfoVo;
import com.danbro.product.controller.vo.PmsAttrDetailVo;
import com.danbro.product.controller.vo.PmsProductAttrValueVo;
import com.danbro.product.entity.PmsAttr;
import com.danbro.product.service.PmsAttrService;
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
@Api(tags = "商品属性(PmsAttr)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("product/attr")
@Setter
public class PmsAttrController {
    private PmsAttrService pmsAttrService;

    @ApiOperation("分页查询属性")
    @GetMapping("{attrType}/list/{categoryId}")
    public ResultPageBean<PmsAttrBaseInfoVo, PmsAttr> getBaseAttrList(@PathVariable Long categoryId,
                                                                      @PathVariable String attrType,
                                                                      @RequestParam("page") Long page,
                                                                      @RequestParam("limit") Long limit,
                                                                      @RequestParam(value = "key", required = false) String key) {
        PageParam<PmsAttr> pageParam = new PageParam<PmsAttr>().setPage(page).setLimit(limit);
        return ResultPageBean.ofSuccess(pmsAttrService.attrQueryPage(pageParam, key, categoryId, attrType));
    }

    @ApiOperation("添加属性")
    @PostMapping("")
    public ResultBean<PmsAttrDetailVo> insertBaseAttr(@Validated(Insert.class) @RequestBody PmsAttrDetailVo param) {
        return ResultBean.ofSuccess(pmsAttrService.insertAttr(param));
    }

    @ApiOperation("更新属性")
    @PutMapping("")
    public ResultBean<PmsAttrDetailVo> updateBaseAttr(@Validated(Update.class) @RequestBody PmsAttrDetailVo param) {
        return ResultBean.ofSuccess(pmsAttrService.updateAttr(param));
    }

    @ApiOperation("获取属性的详细信息")
    @GetMapping("info/{attrId}")
    public ResultBean<PmsAttrDetailVo> getAttrInfo(@PathVariable Long attrId) {
        return ResultBean.ofSuccess(pmsAttrService.getAttrById(attrId));
    }

    @ApiOperation("批量删除属性")
    @DeleteMapping("")
    public ResultBean<?> batchDeleteAttr(@RequestBody Long[] ids) {
        pmsAttrService.batchDeleteAttr(ids);
        return ResultBean.ofSuccess();
    }

    @ApiOperation("查看spu的规格参数")
    @GetMapping("base/listforspu/{spuId}")
    public ResultBean<List<PmsProductAttrValueVo>> getSpuBaseAttrList(@PathVariable Long spuId) {
        return ResultBean.ofSuccess(pmsAttrService.getSpuBaseAttrListBySpuId(spuId));
    }

    @ApiOperation("更新spu的规格参数")
    @PutMapping("{spuId}")
    public ResultBean<?> batchUpdateSpuBaseAttr(@RequestBody List<PmsProductAttrValueVo> productAttrValueVoList,
                                                @PathVariable Long spuId) {
        return ResultBean.ofSuccess(pmsAttrService.batchUpdateSpuBaseAttr(productAttrValueVoList, spuId));
    }
}