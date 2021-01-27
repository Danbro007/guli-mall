package com.danbro.ware.entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:31:11
 */
@Data
@Accessors(chain = true)
@ApiModel("仓库信息")
public class WmsWareInfo implements Serializable {
    private static final long serialVersionUID = 314448268956732585L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("仓库名")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("仓库地址")
    @TableField("address")
    private String address;
    
                    @ApiModelProperty("区域编码")
    @TableField("areacode")
    private String areacode;
    

}