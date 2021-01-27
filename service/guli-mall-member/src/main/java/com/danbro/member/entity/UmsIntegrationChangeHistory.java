package com.danbro.member.entity;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:30:58
 */
@Data
@Accessors(chain = true)
@ApiModel("积分变化历史记录")
public class UmsIntegrationChangeHistory implements Serializable {
    private static final long serialVersionUID = 408922287832885569L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("member_id")
    @TableField("member_id")
    private Long memberId;
    
                    @ApiModelProperty("create_time")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("变化的值")
    @TableField("change_count")
    private Integer changeCount;
    
                    @ApiModelProperty("备注")
    @TableField("note")
    private String note;
    
                    @ApiModelProperty("来源[0->购物；1->管理员修改;2->活动]")
    @TableField("source_tyoe")
    private Object sourceTyoe;
    

}