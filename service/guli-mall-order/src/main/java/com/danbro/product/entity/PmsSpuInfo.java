package com.danbro.product.entity;
import java.util.Date;
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
@ApiModel("spu信息")
public class PmsSpuInfo implements Serializable {
    private static final long serialVersionUID = 892641533298661681L;
                    @ApiModelProperty("商品id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("商品名称")
    @TableField("spu_name")
    private String spuName;
    
                    @ApiModelProperty("商品描述")
    @TableField("spu_description")
    private String spuDescription;
    
                    @ApiModelProperty("所属分类id")
    @TableField("catalog_id")
    private Long catalogId;
    
                    @ApiModelProperty("品牌id")
    @TableField("brand_id")
    private Long brandId;
    
                    @ApiModelProperty("$column.comment")
    @TableField("weight")
    private Double weight;
    
                    @ApiModelProperty("上架状态[0 - 下架，1 - 上架]")
    @TableField("publish_status")
    private Object publishStatus;
    
                    @ApiModelProperty("$column.comment")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("$column.comment")
    @TableField("update_time")
    private Date updateTime;
    

}