package com.danbro.product.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.product.controller.vo.PmsAttrBaseInfoVo;
import com.danbro.product.controller.vo.PmsAttrDetailVo;
import com.danbro.product.entity.PmsAttr;
import com.danbro.product.service.PmsAttrService;
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
@Api(tags = "商品属性(PmsAttr)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("product/attr")
@Setter
public class PmsAttrController {
    private PmsAttrService pmsAttrService;

    @GetMapping("base/list/{categoryId}")
    public ResultPageBean<PmsAttrBaseInfoVo, PmsAttr> getBaseAttrList(@PathVariable Long categoryId,
                                                                      @RequestParam("page") Long page,
                                                                      @RequestParam("limit") Long limit,
                                                                      @RequestParam(value = "key", required = false) String key) {
        PageParam<PmsAttr> pageParam = new PageParam<PmsAttr>().setPage(page).setLimit(limit);
        return ResultPageBean.ofSuccess(pmsAttrService.queryPage(pageParam, key, categoryId));
    }

    @PostMapping("")
    public ResultBean<PmsAttrDetailVo> insertBaseAttr(@Validated(Insert.class) @RequestBody PmsAttrDetailVo param) {
        return ResultBean.ofSuccess(pmsAttrService.insertAttr(param));
    }

    @PutMapping("")
    public ResultBean<PmsAttrDetailVo> updateBaseAttr(@Validated(Update.class) @RequestBody PmsAttrDetailVo param) {
        return ResultBean.ofSuccess(pmsAttrService.updateAttr(param));
    }

    @GetMapping("info/{attrId}")
    public ResultBean<PmsAttrDetailVo> getAttrInfo(@PathVariable Long attrId) {
        return ResultBean.ofSuccess(pmsAttrService.getAttrById(attrId));
    }

    @DeleteMapping("")
    public ResultBean<?> batchDeleteAttr(@RequestBody Long[] ids){
        pmsAttrService.batchDeleteAttr(ids);
        return ResultBean.ofSuccess();
    }

}