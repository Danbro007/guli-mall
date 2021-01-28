package com.danbro.ware.entity;

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
 * @since 2021-01-28 19:06:16
 */
@Data
@Accessors(chain = true)
@ApiModel("仓库信息")
public class WmsWareInfo implements Serializable {
    private static final long serialVersionUID = -18980078892622464L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("仓库名")
    private String name;

    @ApiModelProperty("仓库地址")
    private String address;

    @ApiModelProperty("区域编码")
    private String areacode;


}