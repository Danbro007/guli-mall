package com.danbro.product.controller.vo;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsAttrAttrgroupRelation;
import com.danbro.service.common.validtors.groups.Delete;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname PmsAttrAttrgroupRelation
 * @Description TODO
 * @Date 2021/2/8 17:13
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class PmsAttrAttrgroupRelationVo implements Serializable, Converter<PmsAttrAttrgroupRelation, PmsAttrAttrgroupRelationVo> {
    @NotNull(message = "修改时ID必须存在！", groups = Update.class)
    @Null(message = "添加时ID不能存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @NotNull(message = "添加时属性ID必须存在！", groups = Insert.class)
    @NotNull(message = "删除时属性ID必须存在！", groups = Delete.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("属性id")
    private Long attrId;

    @NotNull(message = "删除时属性分组ID必须存在！", groups = Delete.class)
    @NotNull(message = "添加时属性分组ID必须存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("属性分组id")
    private Long attrGroupId;

    @NotNull(message = "添加时属性组内排序必须存在！", groups = Insert.class)
    @ApiModelProperty("属性组内排序")
    private Integer attrSort;

    @Override
    public PmsAttrAttrgroupRelationVo convertToVo(PmsAttrAttrgroupRelation pmsAttrAttrgroupRelation) {
        MyBeanUtils.copyProperties(pmsAttrAttrgroupRelation, this);
        return this;
    }

    @Override
    public PmsAttrAttrgroupRelation convertToEntity() {
        PmsAttrAttrgroupRelation relation = new PmsAttrAttrgroupRelation();
        MyBeanUtils.copyProperties(this, relation);
        return relation;
    }
}
