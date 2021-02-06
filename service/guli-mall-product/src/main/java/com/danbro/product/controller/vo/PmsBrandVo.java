package com.danbro.product.controller.vo;

import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsBrand;
import com.danbro.common.interfaces.ConvertToVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @Classname PmsBrandVo
 * @Description TODO 品牌Vo
 * @Date 2021/2/2 22:28
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@Builder
public class PmsBrandVo implements ConvertToVo<PmsBrand, PmsBrandVo> {
    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("品牌名")
    private String name;

    @ApiModelProperty("品牌logo地址")
    private String logo;

    @ApiModelProperty("介绍")
    private String descript;

    @ApiModelProperty("显示状态[0-不显示；1-显示]")
    private Boolean showStatus;

    @ApiModelProperty("检索首字母")
    private String firstLetter;

    @ApiModelProperty("排序")
    private Integer sort;


    @Override
    public PmsBrandVo convert(PmsBrand pmsBrand) {
        MyBeanUtils.copyProperties(pmsBrand, this);
        return this;
    }
}
