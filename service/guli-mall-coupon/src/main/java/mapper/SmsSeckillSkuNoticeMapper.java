package .mapper;
 
import .entity.SmsSeckillSkuNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 秒杀商品通知订阅(SmsSeckillSkuNotice)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Mapper
public interface SmsSeckillSkuNoticeMapper extends BaseMapper<SmsSeckillSkuNotice>{
 
}