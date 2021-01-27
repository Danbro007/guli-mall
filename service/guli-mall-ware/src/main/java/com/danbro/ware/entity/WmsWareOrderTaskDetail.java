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
@ApiModel("库存工作单")
public class WmsWareOrderTaskDetail implements Serializable {
    private static final long serialVersionUID = -81672093806554558L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("sku_id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("sku_name")
    @TableField("sku_name")
    private String skuName;
    
                    @ApiModelProperty("购买个数")
    @TableField("sku_num")
    private Integer skuNum;
    
                    @ApiModelProperty("工作单id")
    @TableField("task_id")
    private Long taskId;
    

}