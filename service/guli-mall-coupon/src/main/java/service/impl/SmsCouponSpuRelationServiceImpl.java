package .service.impl;
 
import .entity.SmsCouponSpuRelation;
import .mapper.SmsCouponSpuRelationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import .service.SmsCouponSpuRelationService;
import org.springframework.stereotype.Service;
 
/**
 * 优惠券与产品关联(SmsCouponSpuRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Service
public class SmsCouponSpuRelationServiceImpl extends ServiceImpl<SmsCouponSpuRelationMapper, SmsCouponSpuRelation> implements SmsCouponSpuRelationService {
    
}