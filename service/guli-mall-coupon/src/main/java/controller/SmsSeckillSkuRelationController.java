package .controller;
 
import .entity.SmsSeckillSkuRelation;
import .service.SmsSeckillSkuRelationService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "秒杀活动商品关联(SmsSeckillSkuRelation)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSeckillSkuRelation")
public class SmsSeckillSkuRelationController {
    @Autowired
    private  SmsSeckillSkuRelationService smsSeckillSkuRelationService;
 
}