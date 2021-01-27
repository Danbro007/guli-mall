package com.danbro.product.entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Data
@Accessors(chain = true)
@ApiModel("spu信息介绍")
public class PmsSpuInfoDesc implements Serializable {
    private static final long serialVersionUID = 276613587014389605L;
                    @ApiModelProperty("商品id")
    @TableField("spu_id")
    private Long spuId;
    
                    @ApiModelProperty("商品介绍")
    @TableField("decript")
    private Object decript;
    

}