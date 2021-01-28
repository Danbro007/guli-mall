package com.danbro.member.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Data
@Accessors(chain = true)
@ApiModel("会员收货地址")
public class UmsMemberReceiveAddress implements Serializable {
    private static final long serialVersionUID = -24921554758688888L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("member_id")
    private Long memberId;

    @ApiModelProperty("收货人姓名")
    private String name;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮政编码")
    private String postCode;

    @ApiModelProperty("省份/直辖市")
    private String province;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("区")
    private String region;

    @ApiModelProperty("详细地址(街道)")
    private String detailAddress;

    @ApiModelProperty("省市区代码")
    private String areacode;

    @ApiModelProperty("是否默认")
    private Boolean defaultStatus;


}