package com.danbro.search.controller.vo;

import com.danbro.common.utils.MyBeanUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Danrbo
 * @Classname PmsBrandVo
 * @Description TODO
 * @Date 2021/2/24 12:31
 */
@Data
public class PmsBrandVo {

    @JsonSerialize(using = ToStringSerializer.class)
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

}
