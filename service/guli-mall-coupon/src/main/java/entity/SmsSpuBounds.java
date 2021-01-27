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
@ApiModel("商品spu积分设置")
public class SmsSpuBounds implements Serializable {
    private static final long serialVersionUID = -52906659284431091L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("$column.comment")
    @TableField("spu_id")
    private Long spuId;
    
                    @ApiModelProperty("成长积分")
    @TableField("grow_bounds")
    private Double growBounds;
    
                    @ApiModelProperty("购物积分")
    @TableField("buy_bounds")
    private Double buyBounds;
    
                    @ApiModelProperty("优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]")
    @TableField("work")
    private Object work;
    

}