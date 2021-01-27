package .controller;
 
import .entity.SmsSpuBounds;
import .service.SmsSpuBoundsService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "商品spu积分设置(SmsSpuBounds)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSpuBounds")
public class SmsSpuBoundsController {
    @Autowired
    private  SmsSpuBoundsService smsSpuBoundsService;
 
}