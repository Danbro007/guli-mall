package com.danbro.order.controller.vo;

import java.io.Serializable;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.order.entity.PmsSkuImages;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname PmsSkuImagesVo
 * @Description TODO
 * @Date 2021/2/11 21:14
 * @Created by Administrator
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class PmsSkuImagesVo implements Serializable, Converter<PmsSkuImages, PmsSkuImagesVo> {
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("id")
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("sku_id")
    private Long skuId;

    @ApiModelProperty("图片地址")
    private String imgUrl;

    @ApiModelProperty("排序")
    private Integer imgSort;

    @ApiModelProperty("默认图[0 - 不是默认图，1 - 是默认图]")
    private Boolean defaultImg;

    @Override
    public PmsSkuImagesVo convertToVo(PmsSkuImages pmsSkuImages) {
        MyBeanUtils.copyProperties(pmsSkuImages, this);
        return this;
    }

    @Override
    public PmsSkuImages convertToEntity() {
        PmsSkuImages pmsSkuImages = new PmsSkuImages();
        MyBeanUtils.copyProperties(this, pmsSkuImages);
        return pmsSkuImages;
    }
}
