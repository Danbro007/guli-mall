package com.danbro.order.controller.vo;


import com.danbro.service.common.validtors.groups.Insert;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Classname PmsBrandVo
 * @Description TODO 品牌Vo
 * @Date 2021/2/2 22:28
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PmsBrandVo implements Serializable {

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("品牌id")
    private Long brandId;

    @NotBlank(message = "添加时品牌名必须存在！", groups = Insert.class)
    @ApiModelProperty("品牌名")
    private String name;

    @ApiModelProperty("品牌logo地址")
    private String logo;

    @NotBlank(message = "添加时品牌介绍必须存在！", groups = Insert.class)
    @ApiModelProperty("介绍")
    private String descript;

    @ApiModelProperty("显示状态[0-不显示；1-显示]")
    private Boolean showStatus;

    @ApiModelProperty("检索首字母")
    private String firstLetter;

    @ApiModelProperty("排序")
    private Integer sort;

}
