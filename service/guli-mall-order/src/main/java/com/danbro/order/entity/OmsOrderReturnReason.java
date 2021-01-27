package com.danbro.order.entity;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:30:18
 */
@Data
@Accessors(chain = true)
@ApiModel("退货原因")
public class OmsOrderReturnReason implements Serializable {
    private static final long serialVersionUID = -14081160572777201L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("退货原因名")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
    
                    @ApiModelProperty("启用状态")
    @TableField("status")
    private Object status;
    
                    @ApiModelProperty("create_time")
    @TableField("create_time")
    private Date createTime;
    

}