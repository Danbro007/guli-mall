package entity;
import java.util.Date;
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
@ApiModel("秒杀活动")
public class SmsSeckillPromotion implements Serializable {
    private static final long serialVersionUID = 112216080506500637L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("活动标题")
    @TableField("title")
    private String title;
    
                    @ApiModelProperty("开始日期")
    @TableField("start_time")
    private Date startTime;
    
                    @ApiModelProperty("结束日期")
    @TableField("end_time")
    private Date endTime;
    
                    @ApiModelProperty("上下线状态")
    @TableField("status")
    private Object status;
    
                    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
    
                    @ApiModelProperty("创建人")
    @TableField("user_id")
    private Long userId;
    

}