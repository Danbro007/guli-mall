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
@ApiModel("商品属性")
public class PmsAttr implements Serializable {
    private static final long serialVersionUID = 418490187743966688L;
                    @ApiModelProperty("属性id")
    @TableField("attr_id")
    private Long attrId;
    
                    @ApiModelProperty("属性名")
    @TableField("attr_name")
    private String attrName;
    
                    @ApiModelProperty("是否需要检索[0-不需要，1-需要]")
    @TableField("search_type")
    private Object searchType;
    
                    @ApiModelProperty("属性图标")
    @TableField("icon")
    private String icon;
    
                    @ApiModelProperty("可选值列表[用逗号分隔]")
    @TableField("value_select")
    private String valueSelect;
    
                    @ApiModelProperty("属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    @TableField("attr_type")
    private Object attrType;
    
                    @ApiModelProperty("启用状态[0 - 禁用，1 - 启用]")
    @TableField("enable")
    private Long enable;
    
                    @ApiModelProperty("所属分类")
    @TableField("catelog_id")
    private Long catelogId;
    
                    @ApiModelProperty("快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    @TableField("show_desc")
    private Object showDesc;
    

}