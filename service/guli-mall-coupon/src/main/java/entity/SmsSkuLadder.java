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
@ApiModel("商品阶梯价格")
public class SmsSkuLadder implements Serializable {
    private static final long serialVersionUID = -83455952226779676L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("spu_id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("满几件")
    @TableField("full_count")
    private Integer fullCount;
    
                    @ApiModelProperty("打几折")
    @TableField("discount")
    private Double discount;
    
                    @ApiModelProperty("折后价")
    @TableField("price")
    private Double price;
    
                    @ApiModelProperty("是否叠加其他优惠[0-不可叠加，1-可叠加]")
    @TableField("add_other")
    private Object addOther;
    

}