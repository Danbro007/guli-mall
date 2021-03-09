package com.danbro.ware.controller.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Danrbo
 * @Classname FareVo
 * @Description TODO
 * @Date 2021/3/9 15:09
 */
@Data
public class FareVo {
    private BigDecimal fare;
    private UmsMemberReceiveAddressVo address;
}
