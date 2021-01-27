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
@ApiModel("商品库存")
public class WmsWareSku implements Serializable {
    private static final long serialVersionUID = 934474342291703164L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("sku_id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("仓库id")
    @TableField("ware_id")
    private Long wareId;
    
                    @ApiModelProperty("库存数")
    @TableField("stock")
    private Integer stock;
    
                    @ApiModelProperty("sku_name")
    @TableField("sku_name")
    private String skuName;
    
                    @ApiModelProperty("锁定库存")
    @TableField("stock_locked")
    private Integer stockLocked;
    

}