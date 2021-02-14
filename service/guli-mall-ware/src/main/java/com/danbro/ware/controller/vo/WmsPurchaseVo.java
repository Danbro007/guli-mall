package com.danbro.ware.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.danbro.common.enums.wms.PurchaseDetailStatus;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.service.common.validtors.anotations.IsMobile;
import com.danbro.service.common.validtors.anotations.ListValue;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.danbro.ware.entity.WmsPurchase;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-01-28 19:06:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@ApiModel("采购信息")
public class WmsPurchaseVo implements Serializable, Converter<WmsPurchase, WmsPurchaseVo> {
    private static final long serialVersionUID = -58208221687629002L;

    @NotNull(message = "修改时采购单ID必须存在！", groups = Update.class)
    @Null(message = "添加时采购单ID不能存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("采购单id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("采购人id")
    private Long assigneeId;

    @ApiModelProperty("采购人名")
    private String assigneeName;

    @IsMobile(groups = Update.class)
    @ApiModelProperty("联系方式")
    private String phone;

    @Min(value = 0, message = "优先级必须大于等于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("优先级")
    private Integer priority;

    @ListValue(values = {
            PurchaseDetailStatus.NEW,
            PurchaseDetailStatus.ALLOCATED,
            PurchaseDetailStatus.DONE,
            PurchaseDetailStatus.FAILURE,
            PurchaseDetailStatus.PURCHASING
    }, groups = {Insert.class, Update.class})
    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("仓库id")
    private Long wareId;

    @ApiModelProperty("总金额")
    private BigDecimal amount;

    @ApiModelProperty("创建日期")
    private Date createTime;

    @ApiModelProperty("更新日期")
    private Date updateTime;


    @Override
    public WmsPurchaseVo convertToVo(WmsPurchase wmsPurchase) {
        MyBeanUtils.copyProperties(wmsPurchase, this);
        return this;
    }

    @Override
    public WmsPurchase convertToEntity() {
        WmsPurchase wmsPurchase = new WmsPurchase();
        MyBeanUtils.copyProperties(this, wmsPurchase);
        return wmsPurchase;
    }
}