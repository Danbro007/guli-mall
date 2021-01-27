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
@ApiModel("属性分组")
public class PmsAttrGroup implements Serializable {
    private static final long serialVersionUID = 564931989595377632L;
                    @ApiModelProperty("分组id")
    @TableField("attr_group_id")
    private Long attrGroupId;
    
                    @ApiModelProperty("组名")
    @TableField("attr_group_name")
    private String attrGroupName;
    
                    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
    
                    @ApiModelProperty("描述")
    @TableField("descript")
    private String descript;
    
                    @ApiModelProperty("组图标")
    @TableField("icon")
    private String icon;
    
                    @ApiModelProperty("所属分类id")
    @TableField("catelog_id")
    private Long catelogId;
    

}