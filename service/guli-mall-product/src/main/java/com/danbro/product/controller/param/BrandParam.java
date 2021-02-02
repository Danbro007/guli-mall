package com.danbro.product.controller.param;

import com.baomidou.mybatisplus.annotation.TableId;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsBrand;
import com.danbro.service.base.interfaces.ConvertToEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname BrandParam
 * @Description TODO
 * @Date 2021/2/2 23:09
 * @Created by Administrator
 */
@Data
public class BrandParam implements ConvertToEntity<PmsBrand> {

    @ApiModelProperty("品牌id")
    private Long brandId;

    @ApiModelProperty("品牌名")
    private String name;

    @ApiModelProperty("品牌logo地址")
    private String logo;

    @ApiModelProperty("介绍")
    private String descript;

    @ApiModelProperty("显示状态[0-不显示；1-显示]")
    private Boolean showStatus;

    @ApiModelProperty("检索首字母")
    private String firstLetter;

    @ApiModelProperty("排序")
    private Integer sort;


    @Override
    public PmsBrand convertEntity() {
        PmsBrand pmsBrand = new PmsBrand();
        MyBeanUtils.copyProperties(this, pmsBrand);
        return pmsBrand;
    }
}
