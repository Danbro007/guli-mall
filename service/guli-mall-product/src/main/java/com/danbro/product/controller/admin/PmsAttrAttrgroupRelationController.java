package com.danbro.product.controller.admin;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.PmsAttrAttrgroupRelationVo;
import com.danbro.product.service.PmsAttrAttrgroupRelationService;
import com.danbro.service.common.validtors.groups.Delete;
import com.danbro.service.common.validtors.groups.Insert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Api(tags = "属性&属性分组关联(PmsAttrAttrgroupRelation)")
@Validated
@RestController
@AllArgsConstructor
@Setter
@RequestMapping("product/attrgroup/attr/relation")
public class PmsAttrAttrgroupRelationController {
    private PmsAttrAttrgroupRelationService relationService;

    @ApiOperation("添加属性与属性分组的关系")
    @PostMapping("")
    public ResultBean<List<PmsAttrAttrgroupRelationVo>> insertAttrAttrGroupRelation(@Validated(Insert.class) @RequestBody List<PmsAttrAttrgroupRelationVo> param) {
        return ResultBean.ofSuccess(relationService.batchInsertAttrAttrRelation(param));
    }

    @ApiOperation("删除属性与舒服能够分组之间的关系")
    @DeleteMapping("")
    public ResultBean<?> deleteAttrAttrGroupRelation(@Validated(Delete.class) @RequestBody List<PmsAttrAttrgroupRelationVo> paramList) {
        relationService.batchDeleteByAttrIdAndAttrGroupId(paramList);
        return ResultBean.ofSuccess();
    }
}