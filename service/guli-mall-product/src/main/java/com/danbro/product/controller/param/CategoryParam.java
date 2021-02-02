package com.danbro.product.controller.param;

import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsCategory;
import com.danbro.service.base.interfaces.ConvertToEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Danrbo
 * @Classname CategoryVo
 * @Description TODO
 * @Date 2021/2/2 12:43
 */
@Data
public class CategoryParam implements ConvertToEntity<PmsCategory> {
    @ApiModelProperty("分类id")
    private Long catId;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("父分类id")
    private Long parentCid;

    @ApiModelProperty("层级")
    private Integer catLevel;

    @ApiModelProperty("是否显示[0-不显示，1显示]")
    private Boolean showStatus;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("图标地址")
    private String icon;

    @ApiModelProperty("计量单位")
    private String productUnit;

    @ApiModelProperty("商品数量")
    private Integer productCount;

    @Override
    public PmsCategory convertEntity() {
        PmsCategory pmsCategory = new PmsCategory();
        MyBeanUtils.copyProperties(this, pmsCategory);
        return pmsCategory;
    }
}
