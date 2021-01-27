package .service.impl;
 
import .entity.SmsSeckillSession;
import .mapper.SmsSeckillSessionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import .service.SmsSeckillSessionService;
import org.springframework.stereotype.Service;
 
/**
 * 秒杀活动场次(SmsSeckillSession)表服务实现类
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Service
public class SmsSeckillSessionServiceImpl extends ServiceImpl<SmsSeckillSessionMapper, SmsSeckillSession> implements SmsSeckillSessionService {
    
}