package com.danbro.product.controller.vo.spu;

import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname MemberPrice
 * @Description TODO
 * @Date 2021/2/11 19:44
 * @Created by Administrator
 */
@Data
public class MemberPrice implements Serializable {
    @ApiModelProperty("会员等级ID")
    private Long id;

    @ApiModelProperty("会员等级名")
    private String name;

    @ApiModelProperty("会员价格")
    private BigDecimal price;

    public Long getId() {
        return this.id;
    }

}
