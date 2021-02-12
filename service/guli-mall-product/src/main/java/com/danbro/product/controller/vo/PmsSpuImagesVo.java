package com.danbro.product.controller.vo;

import java.io.Serializable;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsSpuImages;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname PmsSpuImagesVo
 * @Description TODO
 * @Date 2021/2/11 20:32
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class PmsSpuImagesVo implements Serializable, Converter<PmsSpuImages, PmsSpuImagesVo> {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("spu_id")
    private Long spuId;

    @ApiModelProperty("图片名")
    private String imgName;

    @ApiModelProperty("图片地址")
    private String imgUrl;

    @ApiModelProperty("顺序")
    private Integer imgSort;

    @ApiModelProperty("是否默认图")
    private Boolean defaultImg;

    @Override
    public PmsSpuImagesVo convertToVo(PmsSpuImages pmsSpuImages) {
        MyBeanUtils.copyProperties(pmsSpuImages, this);
        return this;
    }

    @Override
    public PmsSpuImages convertToEntity() {
        PmsSpuImages pmsSpuImages = new PmsSpuImages();
        MyBeanUtils.copyProperties(this, pmsSpuImages);
        return pmsSpuImages;
    }
}
