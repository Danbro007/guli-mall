package com.danbro.ware.controller.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Classname PurchaseDetailReason
 * @Description TODO
 * @Date 2021/2/14 22:20
 * @Created by Administrator
 */
@Data
@ApiModel("完成采购项的信息")
public class DonePurchaseDetailVo {
    /**
     * 采购项ID
     */
    private Long itemId;

    /**
     * 采购项状态
     */
    private Integer status;

    /**
     * 如果采购失败则显示失败原因
     */
    private String reason;
}
