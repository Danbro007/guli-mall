package com.danbro.product.controller.admin;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.common.utils.Pagination;
import com.danbro.product.controller.vo.PmsAttrAttrgroupRelationVo;
import com.danbro.product.controller.vo.PmsAttrBaseInfoVo;
import com.danbro.product.controller.vo.PmsAttrDetailVo;
import com.danbro.product.controller.vo.PmsAttrGroupVo;
import com.danbro.product.entity.PmsAttr;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.product.service.PmsAttrGroupService;
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
@Api(tags = "属性分组(PmsAttrGroup)")
@RestController
@AllArgsConstructor
@Setter
@RequestMapping("product/attrgroup")
public class PmsAttrGroupController {
    private PmsAttrGroupService pmsAttrGroupService;

    @ApiOperation("获取分类下的所有属性分组")
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

    @ApiOperation("添加属性分组")
    @PostMapping("")
    public ResultBean<PmsAttrGroupVo> insertAttrGroup(@Validated(Insert.class) @RequestBody PmsAttrGroupVo param) {
        return ResultBean.ofSuccess(pmsAttrGroupService.insertOrUpdate(param));
    }

    @ApiOperation("添加属性分组")
    @PutMapping("")
    public ResultBean<PmsAttrGroupVo> updateAttrGroup(@Validated(Update.class) @RequestBody PmsAttrGroupVo param) {
        return ResultBean.ofSuccess(pmsAttrGroupService.insertOrUpdate(param));
    }

    @ApiOperation("获取属性分组的详细信息")
    @GetMapping("info/{attrGroupId}")
    public ResultBean<PmsAttrGroupVo> getAttrGroupInfo(@PathVariable Long attrGroupId) {
        return ResultBean.ofSuccess(pmsAttrGroupService.getAttrGroupInfo(attrGroupId, true));
    }

    @ApiOperation("批量删除属性分组")
    @DeleteMapping("")
    public ResultBean<?> batchDeleteAttrGroup(@RequestBody Long[] ids) {
        pmsAttrGroupService.batchDeleteAttrGroup(ids);
        return ResultBean.ofSuccess();
    }

    @ApiOperation("获取指定属性分组下的所有属性")
    @GetMapping("{attrGroupId}/attr/relation")
    public ResultBean<List<PmsAttrBaseInfoVo>> getAttrListByAttrGroupId(@PathVariable Long attrGroupId) {
        return ResultBean.ofSuccess(pmsAttrGroupService.getAttrListByAttrGroupId(attrGroupId, true));
    }


    @ApiOperation("查询还没有在指定属性分组下的所有属性")
    @GetMapping("{attrGroupId}/noattr/relation")
    public ResultPageBean<PmsAttrBaseInfoVo, PmsAttr> getNoAttrListByAttrGroupId(@PathVariable Long attrGroupId,
                                                                             @RequestParam("page") Long page,
                                                                             @RequestParam("limit") Long limit,
                                                                             @RequestParam(value = "sidx", required = false) String sidx,
                                                                             @RequestParam(value = "order", required = false) String order,
                                                                             @RequestParam(value = "key", required = false) String key) {
        PageParam<PmsAttr> pageParam = new PageParam<>(page, limit, sidx, order);
        return ResultPageBean.ofSuccess(pmsAttrGroupService.getNoAttrListByAttrGroupId(pageParam, attrGroupId, key, true));
    }

    @ApiOperation("获取分类下所有分组关联的基本属性")
    @GetMapping("{catId}/withattr")
    public ResultBean<List<PmsAttrGroupVo>> getAttrGroupAndAttr(@PathVariable Long catId){
        return ResultBean.ofSuccess(pmsAttrGroupService.getAttrGroupAndAttrByCatId(catId));
    }

}