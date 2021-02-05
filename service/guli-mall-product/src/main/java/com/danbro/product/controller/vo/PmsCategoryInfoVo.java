package com.danbro.product.controller.vo;

import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsCategory;
import com.danbro.common.interfaces.ConvertToVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Danrbo
 * @Classname PmsCategoryInfoVo
 * @Description TODO 分类详情
 * @Date 2021/2/2 13:51
 */
@Data
public class PmsCategoryInfoVo implements Serializable, ConvertToVo<PmsCategory, PmsCategoryInfoVo> {
    @ApiModelProperty("分类id")
    private Long catId;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("父分类id")
    private Long parentCid;

    @ApiModelProperty("层级")
    private Integer catLevel;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("图标地址")
    private String icon;

    @ApiModelProperty("计量单位")
    private String productUnit;

    @ApiModelProperty("商品数量")
    private Integer productCount;

    @Override
    public PmsCategoryInfoVo convert(PmsCategory pmsCategory) {
        MyBeanUtils.copyProperties(pmsCategory, this);
        return this;
    }
}
