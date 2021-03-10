package com.danbro.order.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Classname PmsSpuInfoVo
 * @Description TODO spu信息vo
 * @Date 2021/2/11 19:09
 * @Created by Administrator
 */
@Data
public class PmsSpuInfoVo implements Serializable {
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

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("修改时间")
    private Date updateTime;

}
