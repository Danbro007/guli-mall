package com.danbro.common.enums.oms;

/**
 * @author Danrbo
 * @Classname OrderStatus
 * @Description TODO 订单状态
 * @Date 2021/3/11 11:24
 */

public class OrderStatus {
    /**
     * 待付款
     */
    public static Integer WAIT_PAY = 0;
    /**
     * 已关闭
     */
    public static Integer CLOSED = 1;
    /**
     * 无效订单
     */
    public static Integer INVALID_ORDER = 2;
    /**
     * 待发货
     */
    public static Integer WAIT_DELIVER = 3;
    /**
     * 已发货
     */
    public static Integer DELIVERED = 4;
    /**
     * 已完成
     */
    public static Integer FINISHED = 5;

}
