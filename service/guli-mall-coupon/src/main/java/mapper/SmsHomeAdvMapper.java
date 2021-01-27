package .mapper;
 
import .entity.SmsHomeAdv;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 首页轮播广告(SmsHomeAdv)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Mapper
public interface SmsHomeAdvMapper extends BaseMapper<SmsHomeAdv>{
 
}