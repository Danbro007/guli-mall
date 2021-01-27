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
@ApiModel("秒杀活动商品关联")
public class SmsSeckillSkuRelation implements Serializable {
    private static final long serialVersionUID = 310934133341547149L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("活动id")
    @TableField("promotion_id")
    private Long promotionId;
    
                    @ApiModelProperty("活动场次id")
    @TableField("promotion_session_id")
    private Long promotionSessionId;
    
                    @ApiModelProperty("商品id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("秒杀价格")
    @TableField("seckill_price")
    private Double seckillPrice;
    
                    @ApiModelProperty("秒杀总量")
    @TableField("seckill_count")
    private Double seckillCount;
    
                    @ApiModelProperty("每人限购数量")
    @TableField("seckill_limit")
    private Double seckillLimit;
    
                    @ApiModelProperty("排序")
    @TableField("seckill_sort")
    private Integer seckillSort;
    

}