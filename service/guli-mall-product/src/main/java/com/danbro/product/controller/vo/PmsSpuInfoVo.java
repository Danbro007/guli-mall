package com.danbro.product.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.controller.vo.spu.BaseAttr;
import com.danbro.product.entity.PmsSpuInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname PmsSpuInfoVo
 * @Description TODO spu信息vo
 * @Date 2021/2/11 19:09
 * @Created by Administrator
 */
@Data
public class PmsSpuInfoVo implements Serializable, Converter<PmsSpuInfo, PmsSpuInfoVo> {
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
    private Integer publishStatus;

    @ApiModelProperty("Spu介绍")
    private List<String> decript;

    @ApiModelProperty("Spu图片")
    private List<String> images;

    @ApiModelProperty("积分")
    private SmsSpuBondsVo bounds;

    @ApiModelProperty("基本属性")
    private List<BaseAttr> baseAttrs;

    @ApiModelProperty("sku信息")
    private List<PmsSkuInfoVo> skus;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;


    @Override
    public PmsSpuInfoVo convertToVo(PmsSpuInfo pmsSpuInfo) {
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
