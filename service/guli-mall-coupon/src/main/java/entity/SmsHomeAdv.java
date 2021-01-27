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
@ApiModel("首页轮播广告")
public class SmsHomeAdv implements Serializable {
    private static final long serialVersionUID = -14972376200131937L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("名字")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("图片地址")
    @TableField("pic")
    private String pic;
    
                    @ApiModelProperty("开始时间")
    @TableField("start_time")
    private Date startTime;
    
                    @ApiModelProperty("结束时间")
    @TableField("end_time")
    private Date endTime;
    
                    @ApiModelProperty("状态")
    @TableField("status")
    private Object status;
    
                    @ApiModelProperty("点击数")
    @TableField("click_count")
    private Integer clickCount;
    
                    @ApiModelProperty("广告详情连接地址")
    @TableField("url")
    private String url;
    
                    @ApiModelProperty("备注")
    @TableField("note")
    private String note;
    
                    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
    
                    @ApiModelProperty("发布者")
    @TableField("publisher_id")
    private Long publisherId;
    
                    @ApiModelProperty("审核者")
    @TableField("auth_id")
    private Long authId;
    

}