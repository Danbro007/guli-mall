package com.danbro.ware.entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:31:11
 */
@Data
@Accessors(chain = true)
@ApiModel("$tableInfo.comment")
public class WmsPurchaseDetail implements Serializable {
    private static final long serialVersionUID = 816578843597101679L;
                    @ApiModelProperty("$column.comment")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("采购单id")
    @TableField("purchase_id")
    private Long purchaseId;
    
                    @ApiModelProperty("采购商品id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("采购数量")
    @TableField("sku_num")
    private Integer skuNum;
    
                    @ApiModelProperty("采购金额")
    @TableField("sku_price")
    private Double skuPrice;
    
                    @ApiModelProperty("仓库id")
    @TableField("ware_id")
    private Long wareId;
    
                    @ApiModelProperty("状态[0新建，1已分配，2正在采购，3已完成，4采购失败]")
    @TableField("status")
    private Integer status;
    

}