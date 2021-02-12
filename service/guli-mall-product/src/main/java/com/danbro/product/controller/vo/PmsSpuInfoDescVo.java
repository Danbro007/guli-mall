package com.danbro.product.controller.vo;

import java.io.Serializable;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsSpuInfoDesc;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname PmsSpuInfoDescVo
 * @Description TODO Spu介绍图片Vo
 * @Date 2021/2/11 20:42
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class PmsSpuInfoDescVo implements Serializable, Converter<PmsSpuInfoDesc, PmsSpuInfoDescVo> {
    @ApiModelProperty("商品id")
    private Long spuId;

    @ApiModelProperty("商品介绍图片地址,多个的话用逗号分隔")
    private String decript;


    @Override
    public PmsSpuInfoDescVo convertToVo(PmsSpuInfoDesc pmsSpuInfoDesc) {
        MyBeanUtils.copyProperties(pmsSpuInfoDesc, this);
        return this;
    }

    @Override
    public PmsSpuInfoDesc convertToEntity() {
        PmsSpuInfoDesc pmsSpuInfoDesc = new PmsSpuInfoDesc();
        MyBeanUtils.copyProperties(this, pmsSpuInfoDesc);
        return pmsSpuInfoDesc;
    }
}
