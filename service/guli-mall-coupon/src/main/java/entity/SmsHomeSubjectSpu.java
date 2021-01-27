package entity;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Data
@Accessors(chain = true)
@ApiModel("专题商品")
public class SmsHomeSubjectSpu implements Serializable {
    private static final long serialVersionUID = 326500153568152174L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("专题名字")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("专题id")
    @TableField("subject_id")
    private Long subjectId;
    
                    @ApiModelProperty("spu_id")
    @TableField("spu_id")
    private Long spuId;
    
                    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
    

}