package com.danbro.ware.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Danrbo
 * @Classname WmsLockStockResultVo
 * @Description TODO 锁库存结果vo
 * @Date 2021/3/10 14:01
 */
@Data
@Accessors(chain = true)
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
}
