package com.danbro.product.controller;

import com.danbro.common.entity.ResultPageBean;
import com.danbro.product.service.PmsAttrGroupService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Api(tags = "属性分组(PmsAttrGroup)")
@RestController
@AllArgsConstructor
@RequestMapping("product/attrgroup")
public class PmsAttrGroupController {
    @Autowired
    private PmsAttrGroupService pmsAttrGroupService;

    @GetMapping("list/{categoryId}")
    public ResultPageBean getAttrGroupList(@PathVariable Long categoryId,
                                           @RequestParam("page") Long page,
                                           @RequestParam("limit") Long limit,
                                           @RequestParam(value = "sidx",required = false) String sidx,
                                           @RequestParam(value = "order",required = false) String order,
                                           @RequestParam(value = "key",required = false) String key) {
        return pmsAttrGroupService.getAttrGroupList(categoryId, page, limit, sidx, order, key);
    }

}