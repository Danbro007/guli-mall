package com.danbro.product.entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 22:02:42
 */
@Data
@Accessors(chain = true)
@ApiModel("属性&属性分组关联")
public class PmsAttrAttrgroupRelation implements Serializable {
    private static final long serialVersionUID = -51284374339805981L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("属性id")
    @TableField("attr_id")
    private Long attrId;
    
                    @ApiModelProperty("属性分组id")
    @TableField("attr_group_id")
    private Long attrGroupId;
    
                    @ApiModelProperty("属性组内排序")
    @TableField("attr_sort")
    private Integer attrSort;
    

}