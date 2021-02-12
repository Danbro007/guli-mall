/**
 * Copyright 2021 json.cn
 */
package com.danbro.product.controller.vo.spu;

import cn.hutool.core.annotation.Alias;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Auto-generated: 2021-02-11 19:7:8
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class BaseAttr {
    @ApiModelProperty("属性ID")
    private Long attrId;

    @Alias("attrValue")
    @ApiModelProperty("属性值")
    private String attrValues;

    @ApiModelProperty("是否快速展示")
    private Boolean showDesc;

}