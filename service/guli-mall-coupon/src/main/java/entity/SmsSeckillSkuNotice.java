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
@ApiModel("秒杀商品通知订阅")
public class SmsSeckillSkuNotice implements Serializable {
    private static final long serialVersionUID = 942205343521084665L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("member_id")
    @TableField("member_id")
    private Long memberId;
    
                    @ApiModelProperty("sku_id")
    @TableField("sku_id")
    private Long skuId;
    
                    @ApiModelProperty("活动场次id")
    @TableField("session_id")
    private Long sessionId;
    
                    @ApiModelProperty("订阅时间")
    @TableField("subcribe_time")
    private Date subcribeTime;
    
                    @ApiModelProperty("发送时间")
    @TableField("send_time")
    private Date sendTime;
    
                    @ApiModelProperty("通知方式[0-短信，1-邮件]")
    @TableField("notice_type")
    private Object noticeType;
    

}