package com.danbro.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Data
@Accessors(chain = true)
@ApiModel("品牌")
public class PmsBrand implements Serializable {
    private static final long serialVersionUID = 702301661360306611L;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
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