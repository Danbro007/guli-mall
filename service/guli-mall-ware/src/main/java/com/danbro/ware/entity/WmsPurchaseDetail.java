package com.danbro.ware.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author makejava
 * @since 2021-01-28 19:06:16
 */
@Data
@Accessors(chain = true)
@ApiModel("${tableInfo.comment}")
public class WmsPurchaseDetail implements Serializable {
    private static final long serialVersionUID = -25537541194269838L;

    @TableId
    private Long id;

    @ApiModelProperty("采购单id")
    private Long purchaseId;

    @ApiModelProperty("采购商品id")
    private Long skuId;

    @ApiModelProperty("采购数量")
    private Integer skuNum;

    @ApiModelProperty("采购金额")
    private BigDecimal skuPrice;

    @ApiModelProperty("仓库id")
    private Long wareId;

    @ApiModelProperty("状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
    private Integer status;


}