package .mapper;
 
import .entity.SmsCouponSpuRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
 
/**
 * 优惠券与产品关联(SmsCouponSpuRelation)表数据库访问层
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Mapper
public interface SmsCouponSpuRelationMapper extends BaseMapper<SmsCouponSpuRelation>{
 
}