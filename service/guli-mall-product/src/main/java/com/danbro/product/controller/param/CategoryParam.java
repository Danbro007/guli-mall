package com.danbro.product.controller.param;

import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsCategory;
import com.danbro.common.interfaces.ConvertToEntity;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author Danrbo
 * @Classname CategoryVo
 * @Description TODO
 * @Date 2021/2/2 12:43
 */
@Data
public class CategoryParam implements ConvertToEntity<PmsCategory> {
    @NotNull(message = "修改时分类ID必须存在！", groups = Update.class)
    @Null(message = "添加时分类ID不能存在！", groups = Insert.class)
    @ApiModelProperty("分类id")
    private Long catId;

    @NotBlank(message = "添加时分类名称名必须存在！", groups = Insert.class)
    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("父分类id")
    private Long parentCid;

    @NotNull(message = "添加时层级必须存在！", groups = Insert.class)
    @ApiModelProperty("层级")
    private Integer catLevel;


    @ApiModelProperty("是否显示[0-不显示，1显示]")
    private Boolean showStatus;

    @NotNull(message = "添加时排序必须存在！", groups = Insert.class)
    @ApiModelProperty("排序")
    private Integer sort;

    @URL(message = "图标地址不符合格式！", groups = {Insert.class, Update.class})
    @NotBlank(message = "添加时图标地址名必须存在！", groups = Insert.class)
    @ApiModelProperty("图标地址")
    private String icon;

    @NotBlank(message = "添加时计量单位必须存在！", groups = Insert.class)
    @ApiModelProperty("计量单位")
    private String productUnit;

    @Min(value = 0, message = "商品数量必须大于等于 0！")
    @ApiModelProperty("商品数量")
    private Integer productCount;

    @Override
    public PmsCategory convertEntity() {
        PmsCategory pmsCategory = new PmsCategory();
        MyBeanUtils.copyProperties(this, pmsCategory);
        return pmsCategory;
    }
}
