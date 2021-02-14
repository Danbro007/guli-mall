package com.danbro.ware.controller.vo;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.danbro.ware.entity.WmsWareSku;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname WmsWareSkuVo
 * @Description TODO
 * @Date 2021/2/14 16:56
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class WmsWareSkuVo implements Converter<WmsWareSku, WmsWareSkuVo> {

    @NotNull(message = "修改时库存ID必须存在！", groups = Update.class)
    @Null(message = "添加时库存ID不能存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @NotNull(message = "添加时商品ID必须存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("sku_id")
    private Long skuId;

    @NotNull(message = "添加时仓库ID必须存在！", groups = Insert.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("仓库id")
    private Long wareId;

    @Min(value = 0, message = "库存数必须大于等于0！", groups = {Insert.class, Update.class})
    @NotNull(message = "添加时库存数必须存在！", groups = Insert.class)
    @ApiModelProperty("库存数")
    private Integer stock;

    @NotBlank(message = "添加时商品名必须存在！",groups = Insert.class)
    @ApiModelProperty("sku_name")
    private String skuName;

    @Min(value = 0, message = "锁定库存必须大于等于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("锁定库存")
    private Integer stockLocked;

    @Override
    public WmsWareSkuVo convertToVo(WmsWareSku wmsWareSku) {
        MyBeanUtils.copyProperties(wmsWareSku, this);
        return this;
    }

    @Override
    public WmsWareSku convertToEntity() {
        WmsWareSku wmsWareSku = new WmsWareSku();
        MyBeanUtils.copyProperties(this, wmsWareSku);
        return wmsWareSku;
    }
}
