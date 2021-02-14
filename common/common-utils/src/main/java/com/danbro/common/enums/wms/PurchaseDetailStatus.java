package com.danbro.common.enums.wms;

public class PurchaseDetailStatus {
    /**
     * 新建采购
     */
    public static final int NEW = 0;
    /**
     * 采购已分配
     */
    public static final int ALLOCATED = 1;
    /**
     * 采购正在处理
     */
    public static final int PURCHASING = 2;
    /**
     * 采购完成
     */
    public static final int DONE = 3;
    /**
     * 采购失败
     */
    public static final int FAILURE = 4;
}
