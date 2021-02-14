package com.danbro.ware.controller.vo;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.danbro.ware.entity.WmsPurchaseDetail;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname WmsPurchaseDetailVo
 * @Description TODO
 * @Date 2021/2/14 18:43
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class WmsPurchaseDetailVo implements Converter<WmsPurchaseDetail, WmsPurchaseDetailVo> {
    @NotNull(message = "修改时采购单ID必须存在！", groups = Update.class)
    @Null(message = "添加时采购单ID不能存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("采购单id")
    private Long purchaseId;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("采购商品id")
    private Long skuId;

    @ApiModelProperty("采购数量")
    private Integer skuNum;

    @ApiModelProperty("采购金额")
    private BigDecimal skuPrice;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("仓库id")
    private Long wareId;

    @ApiModelProperty("状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
    private Integer status;

    @Override
    public WmsPurchaseDetailVo convertToVo(WmsPurchaseDetail wmsPurchaseDetail) {
        MyBeanUtils.copyProperties(wmsPurchaseDetail, this);
        return this;
    }

    @Override
    public WmsPurchaseDetail convertToEntity() {
        WmsPurchaseDetail wmsPurchaseDetail = new WmsPurchaseDetail();
        MyBeanUtils.copyProperties(this, wmsPurchaseDetail);
        return wmsPurchaseDetail;
    }
}
