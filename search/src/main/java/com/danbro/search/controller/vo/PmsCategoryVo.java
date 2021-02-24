package com.danbro.search.controller.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Danrbo
 * @Classname PmsCategoryVo
 * @Description TODO
 * @Date 2021/2/24 14:12
 */
@Data
public class PmsCategoryVo {

    @ApiModelProperty("分类id")
    private Long catId;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("父分类id")
    private Long parentCid;

    @ApiModelProperty("层级")
    private Integer catLevel;

    @ApiModelProperty("是否显示[0-不显示，1显示]")
    private Boolean showStatus;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("图标地址")
    private String icon;

    @ApiModelProperty("计量单位")
    private String productUnit;

    @ApiModelProperty("商品数量")
    private Integer productCount;

}
