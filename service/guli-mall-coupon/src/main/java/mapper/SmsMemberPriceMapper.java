package .mapper;
 
import .entity.SmsMemberPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 商品会员价格(SmsMemberPrice)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Mapper
public interface SmsMemberPriceMapper extends BaseMapper<SmsMemberPrice>{
 
}