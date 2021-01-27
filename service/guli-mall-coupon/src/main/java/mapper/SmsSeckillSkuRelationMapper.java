package .mapper;
 
import .entity.SmsSeckillSkuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 秒杀活动商品关联(SmsSeckillSkuRelation)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Mapper
public interface SmsSeckillSkuRelationMapper extends BaseMapper<SmsSeckillSkuRelation>{
 
}