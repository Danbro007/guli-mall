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
@ApiModel("首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】")
public class SmsHomeSubject implements Serializable {
    private static final long serialVersionUID = 584674416959166707L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("专题名字")
    private String name;

    @ApiModelProperty("专题标题")
    private String title;

    @ApiModelProperty("专题副标题")
    private String subTitle;

    @ApiModelProperty("显示状态")
    private Boolean status;

    @ApiModelProperty("详情连接")
    private String url;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("专题图片地址")
    private String img;


}