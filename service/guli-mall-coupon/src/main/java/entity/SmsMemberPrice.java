package entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Data
@Accessors(chain = true)
@ApiModel("商品会员价格")
public class SmsMemberPrice implements Serializable {
    private static final long serialVersionUID = -24951965425817665L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("sku_id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("会员等级id")
    @TableField("member_level_id")
    private Long memberLevelId;
    
                    @ApiModelProperty("会员等级名")
    @TableField("member_level_name")
    private String memberLevelName;
    
                    @ApiModelProperty("会员对应价格")
    @TableField("member_price")
    private Double memberPrice;
    
                    @ApiModelProperty("可否叠加其他优惠[0-不可叠加优惠，1-可叠加]")
    @TableField("add_other")
    private Object addOther;
    

}