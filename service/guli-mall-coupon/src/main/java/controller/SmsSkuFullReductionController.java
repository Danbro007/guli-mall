package .controller;
 
import .entity.SmsSkuFullReduction;
import .service.SmsSkuFullReductionService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "商品满减信息(SmsSkuFullReduction)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSkuFullReduction")
public class SmsSkuFullReductionController {
    @Autowired
    private  SmsSkuFullReductionService smsSkuFullReductionService;
 
}