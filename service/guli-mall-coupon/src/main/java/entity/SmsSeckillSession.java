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
@ApiModel("秒杀活动场次")
public class SmsSeckillSession implements Serializable {
    private static final long serialVersionUID = -80364979247322749L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("场次名称")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("每日开始时间")
    @TableField("start_time")
    private Date startTime;
    
                    @ApiModelProperty("每日结束时间")
    @TableField("end_time")
    private Date endTime;
    
                    @ApiModelProperty("启用状态")
    @TableField("status")
    private Object status;
    
                    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private Date createTime;
    

}