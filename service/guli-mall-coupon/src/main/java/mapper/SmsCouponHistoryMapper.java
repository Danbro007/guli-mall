package .mapper;
 
import .entity.SmsCouponHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 优惠券领取历史记录(SmsCouponHistory)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Mapper
public interface SmsCouponHistoryMapper extends BaseMapper<SmsCouponHistory>{
 
}