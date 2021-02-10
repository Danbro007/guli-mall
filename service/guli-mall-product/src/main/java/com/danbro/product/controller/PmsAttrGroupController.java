package com.danbro.product.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.product.controller.vo.PmsAttrGroupVo;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.product.service.PmsAttrGroupService;
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
@Api(tags = "属性分组(PmsAttrGroup)")
@RestController
@AllArgsConstructor
@Setter
@RequestMapping("product/attrgroup")
public class PmsAttrGroupController {
    private PmsAttrGroupService pmsAttrGroupService;

    @GetMapping("list/{categoryId}")
    public ResultPageBean<PmsAttrGroupVo, PmsAttrGroup> getAttrGroupList(@PathVariable Long categoryId,
                                                                         @RequestParam("page") Long page,
                                                                         @RequestParam("limit") Long limit,
                                                                         @RequestParam(value = "sidx", required = false) String sidx,
                                                                         @RequestParam(value = "order", required = false) String order,
                                                                         @RequestParam(value = "key", required = false) String key) {
        PageParam<PmsAttrGroup> pageParam = new PageParam<>(page, limit, sidx, order);
        return ResultPageBean.ofSuccess(pmsAttrGroupService.queryPage(pageParam, categoryId, key));
    }

    @PostMapping("")
    public ResultBean<PmsAttrGroupVo> insertAttrGroup(@Validated(Insert.class) @RequestBody PmsAttrGroupVo param) {
        return ResultBean.ofSuccess(pmsAttrGroupService.insertOrUpdate(param));
    }

    @PutMapping("")
    public ResultBean<PmsAttrGroupVo> updateAttrGroup(@Validated(Update.class) @RequestBody PmsAttrGroupVo param) {
        return ResultBean.ofSuccess(pmsAttrGroupService.insertOrUpdate(param));
    }

    @GetMapping("info/{attrGroupId}")
    public ResultBean<PmsAttrGroupVo> getAttrGroupInfo(@PathVariable Long attrGroupId) {
        return ResultBean.ofSuccess(pmsAttrGroupService.getAttrGroupInfo(attrGroupId,true));
    }

    @DeleteMapping("")
    public ResultBean<?> batchDeleteAttrGroup(@RequestBody Long[] ids) {
        pmsAttrGroupService.batchDeleteAttrGroup(ids);
        return ResultBean.ofSuccess();
    }

}