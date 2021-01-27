package .service.impl;
 
import .entity.SmsSeckillSkuRelation;
import .mapper.SmsSeckillSkuRelationMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import .service.SmsSeckillSkuRelationService;
import org.springframework.stereotype.Service;
 
/**
 * 秒杀活动商品关联(SmsSeckillSkuRelation)表服务实现类
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Service
public class SmsSeckillSkuRelationServiceImpl extends ServiceImpl<SmsSeckillSkuRelationMapper, SmsSeckillSkuRelation> implements SmsSeckillSkuRelationService {
    
}