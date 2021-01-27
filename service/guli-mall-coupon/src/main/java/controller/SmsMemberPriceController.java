package .controller;
 
import .entity.SmsMemberPrice;
import .service.SmsMemberPriceService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "商品会员价格(SmsMemberPrice)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsMemberPrice")
public class SmsMemberPriceController {
    @Autowired
    private  SmsMemberPriceService smsMemberPriceService;
 
}