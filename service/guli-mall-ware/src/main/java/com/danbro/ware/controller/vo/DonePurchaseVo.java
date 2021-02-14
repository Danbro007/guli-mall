package com.danbro.ware.controller.vo;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Classname DonePurchaseVo
 * @Description TODO 完成采购单
 * @Date 2021/2/14 22:19
 * @Created by Administrator
 */
@Data
@ApiModel("完成采购单的请求参数")
public class DonePurchaseVo {

    @NotNull(message = "采购单ID必须存在！")
    private Long id;

    @NotEmpty(message = "采购项不能为空！")
    private List<DonePurchaseDetailVo> items;
}
