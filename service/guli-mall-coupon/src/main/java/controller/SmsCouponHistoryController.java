package .controller;
 
import .entity.SmsCouponHistory;
import .service.SmsCouponHistoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "优惠券领取历史记录(SmsCouponHistory)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsCouponHistory")
public class SmsCouponHistoryController {
    @Autowired
    private  SmsCouponHistoryService smsCouponHistoryService;
 
}