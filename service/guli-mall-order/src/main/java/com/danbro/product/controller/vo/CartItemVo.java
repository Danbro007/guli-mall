package com.danbro.product.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname CartItemVo
 * @Description TODO 购物车里的每种商品Vo
 * @Date 2021/3/3 19:39
 * @Created by Administrator
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class CartItemVo {
    private Long skuId;

    @ApiModelProperty("是否被选中：默认是被选中的。")
    private Boolean check = true;

    private String title;

    private String image;

    private List<String> skuAttr;

    private BigDecimal price;

    private Integer count;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private BigDecimal totalPrice;

    /**
     * 计算出商品总价：商品个数*商品单价
     *
     * @return 商品总价
     */
    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(count));
    }


}
