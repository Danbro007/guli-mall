package .mapper;
 
import .entity.SmsSkuLadder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 商品阶梯价格(SmsSkuLadder)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Mapper
public interface SmsSkuLadderMapper extends BaseMapper<SmsSkuLadder>{
 
}