package com.danbro.cart.controller.vo;

import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Danrbo
 * @Classname PmsSkuImagesVo
 * @Description TODO
 * @Date 2021/3/6 15:55
 */
@Data
@Builder
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class PmsSkuImagesVo implements Serializable {
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

}
