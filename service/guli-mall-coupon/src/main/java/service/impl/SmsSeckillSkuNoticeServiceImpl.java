package .service.impl;
 
import .entity.SmsSeckillSkuNotice;
import .mapper.SmsSeckillSkuNoticeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import .service.SmsSeckillSkuNoticeService;
import org.springframework.stereotype.Service;
 
/**
 * 秒杀商品通知订阅(SmsSeckillSkuNotice)表服务实现类
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Service
public class SmsSeckillSkuNoticeServiceImpl extends ServiceImpl<SmsSeckillSkuNoticeMapper, SmsSeckillSkuNotice> implements SmsSeckillSkuNoticeService {
    
}