package com.danbro.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname SecKillSkuVo
 * @Description TODO
 * @Date 2021/3/17 19:26
 * @Created by Administrator
 */
@Data
@Accessors(chain = true)
public class SecKillSkuDto implements Serializable {
    private String orderSn;
    private Long promotionSessionId;
    private Long skuId;
    private BigDecimal secKillPrice;
    private Integer num;
    private Long memberId;
}
