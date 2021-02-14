package com.danbro.ware.controller.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.common.interfaces.Converter;
import com.danbro.common.utils.MyBeanUtils;
import com.danbro.service.common.validtors.anotations.IsBool;
import com.danbro.service.common.validtors.anotations.isNumber;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import com.danbro.ware.entity.WmsWareInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname WmsWareInfoVo
 * @Description TODO
 * @Date 2021/2/14 17:13
 * @Created by Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class WmsWareInfoVo implements Converter<WmsWareInfo, WmsWareInfoVo> {
    @NotNull(message = "修改时仓库ID必须存在！", groups = Update.class)
    @Null(message = "添加时仓库ID不能存在！", groups = Insert.class)
    @ApiModelProperty("id")
    private Long id;

    @NotBlank(message = "添加时仓库名必须存在！", groups = Insert.class)
    @ApiModelProperty("仓库名")
    private String name;

    @NotBlank(message = "添加时仓库地址必须存在！", groups = Insert.class)
    @ApiModelProperty("仓库地址")
    private String address;

    @isNumber(message = "区域编码必须是数字！", groups = {Insert.class, Update.class})
    @NotBlank(message = "添加时区域编码必须存在！", groups = Insert.class)
    @ApiModelProperty("区域编码")
    private String areacode;

    @Override
    public WmsWareInfoVo convertToVo(WmsWareInfo wmsWareInfo) {
        MyBeanUtils.copyProperties(wmsWareInfo, this);
        return this;
    }

    @Override
    public WmsWareInfo convertToEntity() {
        WmsWareInfo wmsWareInfo = new WmsWareInfo();
        MyBeanUtils.copyProperties(this, wmsWareInfo);
        return wmsWareInfo;
    }
}
