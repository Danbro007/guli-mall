package com.danbro.coupon.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Data
@Accessors(chain = true)
@ApiModel("专题商品")
public class SmsHomeSubjectSpu implements Serializable {
    private static final long serialVersionUID = 600882052761003702L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("专题名字")
    private String name;

    @ApiModelProperty("专题id")
    private Long subjectId;

    @ApiModelProperty("spu_id")
    private Long spuId;

    @ApiModelProperty("排序")
    private Integer sort;


}