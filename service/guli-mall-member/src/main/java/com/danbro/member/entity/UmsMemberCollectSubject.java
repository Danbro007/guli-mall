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
@ApiModel("会员收藏的专题活动")
public class UmsMemberCollectSubject implements Serializable {
    private static final long serialVersionUID = 223475413053536271L;

    @TableId
    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("subject_id")
    private Long subjectId;

    @ApiModelProperty("subject_name")
    private String subjectName;

    @ApiModelProperty("subject_img")
    private String subjectImg;

    @ApiModelProperty("活动url")
    private String subjectUrll;


}