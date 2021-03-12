package com.danbro.ware.controller.vo;

import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Danrbo
 * @Classname OmsOrderItemVo
 * @Description TODO
 * @Date 2021/3/12 16:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class OmsOrderItemVo implements Serializable, Converter<OmsOrderItem, OmsOrderItemVo> {
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("order_id")
    private Long orderId;

    @ApiModelProperty("order_sn")
    private String orderSn;

    @ApiModelProperty("spu_id")
    private Long spuId;

    @ApiModelProperty("spu_name")
    private String spuName;

    @ApiModelProperty("spu_pic")
    private String spuPic;

    @ApiModelProperty("品牌")
    private String spuBrand;

    @ApiModelProperty("商品分类id")
    private Long categoryId;

    @ApiModelProperty("商品sku编号")
    private Long skuId;

    @ApiModelProperty("商品sku名字")
    private String skuName;

    @ApiModelProperty("商品sku图片")
    private String skuPic;

    @ApiModelProperty("商品sku价格")
    private BigDecimal skuPrice;

    @ApiModelProperty("商品购买的数量")
    private Integer skuQuantity;

    @ApiModelProperty("商品销售属性组合（JSON）")
    private String skuAttrsVals;

    @ApiModelProperty("商品促销分解金额")
    private BigDecimal promotionAmount;

    @ApiModelProperty("优惠券优惠分解金额")
    private BigDecimal couponAmount;

    @ApiModelProperty("积分优惠分解金额")
    private BigDecimal integrationAmount;

    @ApiModelProperty("该商品经过优惠后的分解金额")
    private BigDecimal realAmount;

    @ApiModelProperty("赠送积分")
    private Integer giftIntegration;

    @ApiModelProperty("赠送成长值")
    private Integer giftGrowth;


    @Override
    public OmsOrderItemVo convertToVo(OmsOrderItem omsOrderItem) {
        MyBeanUtils.copyProperties(omsOrderItem, this);
        return this;
    }

    @Override
    public OmsOrderItem convertToEntity() {
        OmsOrderItem omsOrderItem = new OmsOrderItem();
        MyBeanUtils.copyProperties(this, omsOrderItem);
        return omsOrderItem;
    }
}
