package com.danbro.order.controller.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Danrbo
 * @Classname FareVo
 * @Description TODO 邮费信息
 * @Date 2021/3/9 15:09
 */
@Data
public class FareVo implements Serializable {
    private BigDecimal fare;
    private UmsMemberReceiveAddressVo address;
}
