package com.danbro.product.controller.param;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.ConvertToEntity;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsCategoryBrandRelation;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname CategoryBrandRelation
 * @Description TODO
 * @Date 2021/2/7 14:55
 * @Created by Administrator
 */
@Data
public class CategoryBrandRelationParam implements ConvertToEntity<PmsCategoryBrandRelation> {

    @NotNull(message = "修改时品牌分类ID必须存在！", groups = Update.class)
    @Null(message = "添加时品牌分类ID不能存在！", groups = Insert.class)
    private Long id;

    @NotNull(message = "添加时品牌ID必须存在！", groups = Insert.class)
    @NotNull(message = "修改时品牌ID必须存在！", groups = Update.class)
    @ApiModelProperty("品牌id")
    private Long brandId;

    @NotNull(message = "添加时分类ID必须存在！", groups = Insert.class)
    @NotNull(message = "修改时分类ID必须存在！", groups = Update.class)
    @ApiModelProperty("分类id")
    private Long catelogId;

    private String brandName;

    private String catelogName;

    @Override
    public PmsCategoryBrandRelation convertEntity() {
        PmsCategoryBrandRelation relation = new PmsCategoryBrandRelation();
        MyBeanUtils.copyProperties(this, relation);
        return relation;
    }
}