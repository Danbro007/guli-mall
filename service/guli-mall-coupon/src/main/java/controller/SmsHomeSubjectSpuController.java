package .controller;
 
import .entity.SmsHomeSubjectSpu;
import .service.SmsHomeSubjectSpuService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "专题商品(SmsHomeSubjectSpu)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsHomeSubjectSpu")
public class SmsHomeSubjectSpuController {
    @Autowired
    private  SmsHomeSubjectSpuService smsHomeSubjectSpuService;
 
}