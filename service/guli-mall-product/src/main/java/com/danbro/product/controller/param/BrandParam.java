package com.danbro.product.controller.param;

import com.danbro.common.utils.MyBeanUtils;
import com.danbro.product.entity.PmsBrand;
import com.danbro.common.interfaces.ConvertToEntity;
import com.danbro.service.common.validtors.anotations.IsBool;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * @author liweimo
 * @Classname BrandParam
 * @Description TODO
 * @Date 2021/2/2 23:09
 */
@Data
public class BrandParam implements ConvertToEntity<PmsBrand> {

    @NotNull(message = "修改时品牌ID必须存在！", groups = Update.class)
    @Null(message = "添加时品牌ID不能存在！", groups = Insert.class)
    @ApiModelProperty("品牌id")
    private Long brandId;

    @NotBlank(message = "添加时品牌名必须存在！", groups = Insert.class)
    @ApiModelProperty("品牌名")
    private String name;

    @NotBlank(message = "添加时 Logo 地址必须存在！", groups = Insert.class)
    @URL(message = "必须是 URL 地址！", groups = {Insert.class, Update.class})
    @ApiModelProperty("品牌logo地址")
    private String logo;

    @NotBlank(message = "添加时品牌介绍必须存在！", groups = Insert.class)
    @ApiModelProperty("介绍")
    private String descript;

    @IsBool(groups = {Insert.class, Update.class})
    @ApiModelProperty("显示状态[0-不显示；1-显示]")
    private Boolean showStatus;

    @NotBlank(message = "添加时检索首字母必须存在！", groups = Insert.class)
    @Pattern(regexp = "^[A-Za-z]$", message = "检索首字母必须是一个英文字母！", groups = {Insert.class, Update.class})
    @ApiModelProperty("检索首字母")
    private String firstLetter;

    @NotNull(message = "添加时排序必须存在！", groups = Insert.class)
    @Min(value = 0, message = "排序必须大于等于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("排序")
    private Integer sort;

    @Override
    public PmsBrand convertEntity() {
        PmsBrand pmsBrand = new PmsBrand();
        MyBeanUtils.copyProperties(this, pmsBrand);
        return pmsBrand;
    }
}
