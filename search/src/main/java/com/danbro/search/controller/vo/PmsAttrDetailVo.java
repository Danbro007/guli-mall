package com.danbro.search.controller.vo;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname PmsAttrDetailVo
 * @Description TODO pms 属性vo
 * @Date 2021/2/23 20:49
 * @Created by Administrator
 */
@Data
public class PmsAttrDetailVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("属性id")
    private Long attrId;

    @ApiModelProperty("属性名")
    private String attrName;

    @ApiModelProperty("是否需要检索[0-不需要，1-需要]")
    private Boolean searchType;

    @ApiModelProperty("属性图标")
    private String icon;

    @ApiModelProperty("可选值列表[用逗号分隔]")
    private String valueSelect;

    @ApiModelProperty("属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Integer attrType;

    @ApiModelProperty("启用状态[0 - 禁用，1 - 启用]")
    private Boolean enable;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long catelogId;

    private Boolean showDesc;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long attrGroupId;

    private Integer valueType;

    private String[] catelogPath;

}
