package com.danbro.cart.controller.vo;

import java.math.BigDecimal;
import java.util.List;
import com.danbro.common.utils.MyCollectionUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @Classname CartVo
 * @Description TODO 购物车Vo
 * @Date 2021/3/3 19:40
 * @Created by Administrator
 */

public class CartVo {
    @Setter
    @Getter
    private List<CartItemVo> items;
    /**
     * 购物车里的商品总个数
     */
    private Integer countNum;

    /**
     * 商品类型个数
     */
    @Getter
    private Integer countType;

    /**
     * 购物车里的商品总价
     */
    private BigDecimal totalAmount;

    /**
     * 减免的价格
     */
    @Getter
    private BigDecimal reduce = new BigDecimal("0.00");

    public Integer getCountNum() {
        return items.size();
    }

    public BigDecimal getTotalAmount() {
        if (MyCollectionUtils.isEmpty(items)) {
            return BigDecimal.ZERO;
        }
        // 过滤掉没有被选中的 check = false
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (CartItemVo item : items) {
            if (item.getCheck()) {
                totalAmount = totalAmount.add(item.getTotalPrice());
            }
        }
        // 减去优惠的价格
        return totalAmount.subtract(getReduce());
    }

}
