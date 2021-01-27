package .mapper;
 
import .entity.SmsSeckillSession;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 秒杀活动场次(SmsSeckillSession)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Mapper
public interface SmsSeckillSessionMapper extends BaseMapper<SmsSeckillSession>{
 
}