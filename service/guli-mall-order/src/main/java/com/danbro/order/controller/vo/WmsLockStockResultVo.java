package com.danbro.order.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Danrbo
 * @Classname WmsLockStockResultVo
 * @Description TODO 锁库存结果vo
 * @Date 2021/3/10 14:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WmsLockStockResultVo implements Serializable {
    /**
     * 商品的skuId
     */
    private Long skuId;
    /**
     * 锁库存的结果
     */
    private Boolean success;
    /**
     * 要锁的库存数
     */
    private Integer stock;
    /**
     * 仓库ID
     */
    private Long wareId;
}
