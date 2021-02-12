/**
 * Copyright 2021 json.cn
 */
package com.danbro.product.controller.vo.spu;

import java.math.BigDecimal;
import java.util.List;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsSpuInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Auto-generated: 2021-02-11 19:7:8
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class Spu implements Converter<PmsSpuInfo, Spu> {

    @ApiModelProperty("商品id")
    private Long id;

    @ApiModelProperty("商品名称")
    private String spuName;

    @ApiModelProperty("商品描述")
    private String spuDescription;

    @ApiModelProperty("所属分类id")
    private Long catalogId;

    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("商品重量")
    private BigDecimal weight;

    @ApiModelProperty("上架状态[0 - 下架，1 - 上架]")
    private Boolean publishStatus;

    @ApiModelProperty("Spu介绍的图片地址")
    private List<String> decript;

    @ApiModelProperty("Spu图片")
    private List<String> images;

    @ApiModelProperty("积分")
    private Bounds bounds;

    @ApiModelProperty("基本属性")
    private List<BaseAttr> baseAttrs;

    @ApiModelProperty("sku信息")
    private List<Skus> skus;

    @Override
    public Spu convertToVo(PmsSpuInfo pmsSpuInfo) {
        MyBeanUtils.copyProperties(pmsSpuInfo, this);
        return this;
    }

    @Override
    public PmsSpuInfo convertToEntity() {
        PmsSpuInfo pmsSpuInfo = new PmsSpuInfo();
        MyBeanUtils.copyProperties(this, pmsSpuInfo);
        return pmsSpuInfo;
    }
}