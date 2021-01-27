package .controller;
 
import .entity.SmsSkuLadder;
import .service.SmsSkuLadderService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "商品阶梯价格(SmsSkuLadder)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSkuLadder")
public class SmsSkuLadderController {
    @Autowired
    private  SmsSkuLadderService smsSkuLadderService;
 
}