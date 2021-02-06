package com.danbro.product.controller.param;

import com.danbro.common.interfaces.ConvertToEntity;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author Danrbo
 * @Classname AttrGroupParam
 * @Description TODO
 * @Date 2021/2/6 22:37
 */
@Data
public class AttrGroupParam implements ConvertToEntity<PmsAttrGroup> {
    @NotNull(message = "修改时分组ID必须存在！", groups = Update.class)
    @Null(message = "添加时分组ID不能存在！", groups = Insert.class)
    @ApiModelProperty("分组id")
    private Long attrGroupId;

    @NotBlank(message = "添加时组名必须存在！", groups = Insert.class)
    @ApiModelProperty("组名")
    private String attrGroupName;

    @NotNull(message = "添加时排序必须存在！", groups = Insert.class)
    @Min(value = 0, message = "排序必须大于等于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("排序")
    private Integer sort;

    @NotBlank(message = "添加时描述必须存在！", groups = Insert.class)
    @ApiModelProperty("描述")
    private String descript;

    @NotBlank(message = "添加时组图标必须存在！", groups = Insert.class)
    @ApiModelProperty("组图标")
    private String icon;

    @NotNull(message = "添加时所属分类ID必须存在！", groups = Insert.class)
    @ApiModelProperty("所属分类id")
    private Long catelogId;

    @Override
    public PmsAttrGroup convertEntity() {
        PmsAttrGroup pmsAttrGroup = new PmsAttrGroup();
        MyBeanUtils.copyProperties(this, pmsAttrGroup);
        return pmsAttrGroup;
    }
}
