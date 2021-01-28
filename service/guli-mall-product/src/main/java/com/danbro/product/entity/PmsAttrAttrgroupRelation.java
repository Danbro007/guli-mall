package com.danbro.product.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author makejava
 * @since 2021-01-28 18:56:54
 */
@Data
@Accessors(chain = true)
@ApiModel("属性&属性分组关联")
public class PmsAttrAttrgroupRelation implements Serializable {
    private static final long serialVersionUID = 371404402667967454L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("属性id")
    private Long attrId;

    @ApiModelProperty("属性分组id")
    private Long attrGroupId;

    @ApiModelProperty("属性组内排序")
    private Integer attrSort;


}