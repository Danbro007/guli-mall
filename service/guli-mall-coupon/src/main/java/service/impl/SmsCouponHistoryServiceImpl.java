package .service.impl;
 
import .entity.SmsCouponHistory;
import .mapper.SmsCouponHistoryMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import .service.SmsCouponHistoryService;
import org.springframework.stereotype.Service;
 
/**
 * 优惠券领取历史记录(SmsCouponHistory)表服务实现类
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Service
public class SmsCouponHistoryServiceImpl extends ServiceImpl<SmsCouponHistoryMapper, SmsCouponHistory> implements SmsCouponHistoryService {
    
}