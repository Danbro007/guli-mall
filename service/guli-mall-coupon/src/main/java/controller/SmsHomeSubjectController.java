package .controller;
 
import .entity.SmsHomeSubject;
import .service.SmsHomeSubjectService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:29:24
 */
@Api(tags = "首页专题表【jd首页下面很多专题，每个专题链接新的页面，展示专题商品信息】(SmsHomeSubject)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("smsHomeSubject")
public class SmsHomeSubjectController {
    @Autowired
    private  SmsHomeSubjectService smsHomeSubjectService;
 
}