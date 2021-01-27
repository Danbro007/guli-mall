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
@ApiModel("会员收藏的专题活动")
public class UmsMemberCollectSubject implements Serializable {
    private static final long serialVersionUID = 523115352122820107L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("subject_id")
    @TableField("subject_id")
    private Long subjectId;
    
                    @ApiModelProperty("subject_name")
    @TableField("subject_name")
    private String subjectName;
    
                    @ApiModelProperty("subject_img")
    @TableField("subject_img")
    private String subjectImg;
    
                    @ApiModelProperty("活动url")
    @TableField("subject_urll")
    private String subjectUrll;
    

}