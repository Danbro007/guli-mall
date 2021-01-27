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
@ApiModel("商品满减信息")
public class SmsSkuFullReduction implements Serializable {
    private static final long serialVersionUID = 546547325313525062L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("spu_id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("满多少")
    @TableField("full_price")
    private Double fullPrice;
    
                    @ApiModelProperty("减多少")
    @TableField("reduce_price")
    private Double reducePrice;
    
                    @ApiModelProperty("是否参与其他优惠")
    @TableField("add_other")
    private Object addOther;
    

}