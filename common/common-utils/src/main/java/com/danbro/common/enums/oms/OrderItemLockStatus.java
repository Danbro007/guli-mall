package com.danbro.common.enums.oms;

/**
 * @author Danrbo
 * @Classname OrderItemLockStatus
 * @Description TODO 订单项库存状态
 * @Date 2021/3/11 11:59
 */
public class OrderItemLockStatus {
    /**
     * 锁定状态
     */
    public static Integer LOCKED = 1;
    /**
     * 解锁状态
     */
    public static Integer UNLOCKED = 2;
    /**
     * 扣减状态
     */
    public static Integer DEDUCTION = 3;

}
