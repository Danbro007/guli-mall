package com.danbro.member.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.member.entity.UmsMemberLevel;
import com.danbro.service.common.validtors.anotations.IsBool;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname UmsMemberLevelVo
 * @Description TODO
 * @Date 2021/2/11 12:29
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class UmsMemberLevelVo implements Serializable, Converter<UmsMemberLevel, UmsMemberLevelVo> {

    @NotNull(message = "修改时会员等级ID不能为空！", groups = Update.class)
    @Null(message = "添加时会员等级ID必须为空！", groups = Insert.class)
    @ApiModelProperty("id")
    private Long id;

    @NotBlank(message = "添加会员等级时等级名称不能为空！", groups = Insert.class)
    @ApiModelProperty("等级名称")
    private String name;

    @NotNull(message = "添加会员等级时成长值")
    @ApiModelProperty("等级需要的成长值")
    private Integer growthPoint;

    @NotNull(message = "添加会员等级时是否为默认等级不能为空！", groups = Insert.class)
    @IsBool(message = "是否为默认等级必须为 【true】:是，【false】:不是！", groups = {Insert.class, Update.class})
    @ApiModelProperty("是否为默认等级[0->不是；1->是]")
    private Boolean defaultStatus;

    @NotNull(message = "添加会员等级时免运费标准不能为空！", groups = Insert.class)
    @Min(value = 0, message = "免运费标准不能小于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("免运费标准")
    private BigDecimal freeFreightPoint;

    @NotNull(message = "添加会员等级时每次评价获取的成长值不能为空！", groups = Insert.class)
    @Min(value = 0, message = "每次评价获取的成长值不能小于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("每次评价获取的成长值")
    private Integer commentGrowthPoint;

    @NotNull(message = "添加会员等级时是否有免邮特权不能为空！", groups = Insert.class)
    @IsBool(groups = {Insert.class, Update.class})
    @ApiModelProperty("是否有免邮特权")
    private Boolean priviledgeFreeFreight;

    @NotNull(message = "添加会员等级时是否有会员价格特权不能为空！", groups = Insert.class)
    @IsBool(groups = {Insert.class, Update.class})
    @ApiModelProperty("是否有会员价格特权")
    private Boolean priviledgeMemberPrice;

    @NotNull(message = "添加会员等级时是否有生日特权不能为空！", groups = Insert.class)
    @IsBool(groups = {Insert.class, Update.class})
    @ApiModelProperty("是否有生日特权")
    private Boolean priviledgeBirthday;

    @NotBlank(message = "添加会员等级时备注不能为空！", groups = Insert.class)
    @ApiModelProperty("备注")
    private String note;

    @Override
    public UmsMemberLevelVo convertToVo(UmsMemberLevel umsMemberLevel) {
        MyBeanUtils.copyProperties(umsMemberLevel, this);
        return this;
    }

    @Override
    public UmsMemberLevel convertToEntity() {
        UmsMemberLevel umsMemberLevel = new UmsMemberLevel();
        MyBeanUtils.copyProperties(this, umsMemberLevel);
        return umsMemberLevel;
    }
}
