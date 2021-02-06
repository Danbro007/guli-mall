package com.danbro.product.controller.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.danbro.common.interfaces.ConvertToVo;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsAttrGroup;
import com.danbro.product.entity.PmsCategory;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author Danrbo
 * @Classname PmsAttrGroupVo
 * @Description TODO
 * @Date 2021/2/5 22:22
 */
public class PmsAttrGroupVo implements Serializable, ConvertToVo<PmsAttrGroup, PmsAttrGroupVo> {

    @ApiModelProperty("分组id")
    private Long attrGroupId;

    @ApiModelProperty("组名")
    private String attrGroupName;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("描述")
    private String descript;

    @ApiModelProperty("组图标")
    private String icon;

    @ApiModelProperty("所属分类id")
    private Long catelogId;


    @Override
    public PmsAttrGroupVo convert(PmsAttrGroup pmsAttrGroup) {
        MyBeanUtils.copyProperties(pmsAttrGroup, this);
        return this;
    }
}