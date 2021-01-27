package .controller;
 
import .entity.SmsSeckillSession;
import .service.SmsSeckillSessionService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "秒杀活动场次(SmsSeckillSession)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsSeckillSession")
public class SmsSeckillSessionController {
    @Autowired
    private  SmsSeckillSessionService smsSeckillSessionService;
 
}