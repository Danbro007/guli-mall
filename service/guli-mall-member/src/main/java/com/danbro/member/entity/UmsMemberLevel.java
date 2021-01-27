package com.danbro.member.entity;
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
@ApiModel("会员等级")
public class UmsMemberLevel implements Serializable {
    private static final long serialVersionUID = 599264142801712181L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("等级名称")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("等级需要的成长值")
    @TableField("growth_point")
    private Integer growthPoint;
    
                    @ApiModelProperty("是否为默认等级[0->不是；1->是]")
    @TableField("default_status")
    private Object defaultStatus;
    
                    @ApiModelProperty("免运费标准")
    @TableField("free_freight_point")
    private Double freeFreightPoint;
    
                    @ApiModelProperty("每次评价获取的成长值")
    @TableField("comment_growth_point")
    private Integer commentGrowthPoint;
    
                    @ApiModelProperty("是否有免邮特权")
    @TableField("priviledge_free_freight")
    private Object priviledgeFreeFreight;
    
                    @ApiModelProperty("是否有会员价格特权")
    @TableField("priviledge_member_price")
    private Object priviledgeMemberPrice;
    
                    @ApiModelProperty("是否有生日特权")
    @TableField("priviledge_birthday")
    private Object priviledgeBirthday;
    
                    @ApiModelProperty("备注")
    @TableField("note")
    private String note;
    

}