package com.danbro.ware.entity;
import java.util.Date;
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
@ApiModel("采购信息")
public class WmsPurchase implements Serializable {
    private static final long serialVersionUID = 982331839633424080L;
                    @ApiModelProperty("采购单id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("采购人id")
    @TableField("assignee_id")
    private Long assigneeId;
    
                    @ApiModelProperty("采购人名")
    @TableField("assignee_name")
    private String assigneeName;
    
                    @ApiModelProperty("联系方式")
    @TableField("phone")
    private String phone;
    
                    @ApiModelProperty("优先级")
    @TableField("priority")
    private Integer priority;
    
                    @ApiModelProperty("状态")
    @TableField("status")
    private Integer status;
    
                    @ApiModelProperty("仓库id")
    @TableField("ware_id")
    private Long wareId;
    
                    @ApiModelProperty("总金额")
    @TableField("amount")
    private Double amount;
    
                    @ApiModelProperty("创建日期")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("更新日期")
    @TableField("update_time")
    private Date updateTime;
    

}