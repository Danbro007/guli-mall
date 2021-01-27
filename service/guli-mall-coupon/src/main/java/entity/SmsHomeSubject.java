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
@ApiModel("首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】")
public class SmsHomeSubject implements Serializable {
    private static final long serialVersionUID = 570459100313699472L;
                    @ApiModelProperty("id")
    @TableField("id")
    private Long id;
    
                    @ApiModelProperty("专题名字")
    @TableField("name")
    private String name;
    
                    @ApiModelProperty("专题标题")
    @TableField("title")
    private String title;
    
                    @ApiModelProperty("专题副标题")
    @TableField("sub_title")
    private String subTitle;
    
                    @ApiModelProperty("显示状态")
    @TableField("status")
    private Object status;
    
                    @ApiModelProperty("详情连接")
    @TableField("url")
    private String url;
    
                    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
    
                    @ApiModelProperty("专题图片地址")
    @TableField("img")
    private String img;
    

}