package com.danbro.member.entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:30:58
 */
@Data
@Accessors(chain = true)
@ApiModel("会员收货地址")
public class UmsMemberReceiveAddress implements Serializable {
    private static final long serialVersionUID = 119973221384267959L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("member_id")
    @TableField("member_id")
    private Long memberId;
    
                    @ApiModelProperty("收货人姓名")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("电话")
    @TableField("phone")
    private String phone;
    
                    @ApiModelProperty("邮政编码")
    @TableField("post_code")
    private String postCode;
    
                    @ApiModelProperty("省份/直辖市")
    @TableField("province")
    private String province;
    
                    @ApiModelProperty("城市")
    @TableField("city")
    private String city;
    
                    @ApiModelProperty("区")
    @TableField("region")
    private String region;
    
                    @ApiModelProperty("详细地址(街道)")
    @TableField("detail_address")
    private String detailAddress;
    
                    @ApiModelProperty("省市区代码")
    @TableField("areacode")
    private String areacode;
    
                    @ApiModelProperty("是否默认")
    @TableField("default_status")
    private Object defaultStatus;
    

}