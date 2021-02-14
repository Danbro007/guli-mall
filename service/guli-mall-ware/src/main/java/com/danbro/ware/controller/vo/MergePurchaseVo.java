package com.danbro.ware.controller.vo;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import com.danbro.service.common.validtors.groups.Insert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname MergePurchaseVo
 * @Description TODO 合并采购单的请求参数
 * @Date 2021/2/14 20:46
 * @Created by Administrator
 */
@Data
@ApiModel("合并采购单的请求参数")
public class MergePurchaseVo {
    @ApiModelProperty("采购信息ID")
    private Long purchaseId;

    @NotEmpty(message = "合并采购单时采购单的ID不能为空！", groups = Insert.class)
    @ApiModelProperty("采购单的ID列表")
    private List<Long> items;
}
