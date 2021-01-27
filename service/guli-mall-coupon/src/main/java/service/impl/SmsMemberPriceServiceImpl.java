package .service.impl;
 
import .entity.SmsMemberPrice;
import .mapper.SmsMemberPriceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import .service.SmsMemberPriceService;
import org.springframework.stereotype.Service;
 
/**
 * 商品会员价格(SmsMemberPrice)表服务实现类
 *
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Service
public class SmsMemberPriceServiceImpl extends ServiceImpl<SmsMemberPriceMapper, SmsMemberPrice> implements SmsMemberPriceService {
    
}