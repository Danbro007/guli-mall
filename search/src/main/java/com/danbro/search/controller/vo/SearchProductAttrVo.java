package com.danbro.search.controller.vo;

import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Classname PmsAttr
 * @Description TODO
 * @Date 2021/2/15 21:29
 * @Created by Administrator
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class SearchProductAttrVo implements Serializable {
    @ApiModelProperty("属性ID")
    @Field(type = FieldType.Long)
    private Long attrId;

    @ApiModelProperty("属性名")
    @Field(type = FieldType.Keyword, index = false, docValues = false)
    private String attrName;

    @ApiModelProperty("属性值")
    @Field(type = FieldType.Keyword)
    private String attrValue;
}
