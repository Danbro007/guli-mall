package com.danbro.product.controller.vo;

import java.io.Serializable;
import java.util.List;

import com.danbro.service.base.interfaces.ConvertToVo;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liweimo
 * @Classname PmsCategoryVo
 * @Description TODO
 * @Date 2021/1/28 21:26
 * @Created by Administrator
 */
@Data
public class PmsCategoryVo implements Serializable, ConvertToVo<PmsCategory, PmsCategoryVo> {
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

    @ApiModelProperty("子分类")
    private List<PmsCategoryVo> children;

    @Override
    public PmsCategoryVo convert(PmsCategory pmsCategory) {
        MyBeanUtils.copyProperties(pmsCategory, this);
        return this;
    }
}
