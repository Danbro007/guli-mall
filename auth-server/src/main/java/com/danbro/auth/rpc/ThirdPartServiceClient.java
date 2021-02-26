package com.danbro.auth.rpc;

import com.danbro.common.entity.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Danrbo
 * @Classname ThirdPartServiceClient
 * @Description TODO 第三方服务客户端
 * @Date 2021/2/25 22:00
 */
@Component
@FeignClient(value = "service-third-part")
public interface ThirdPartServiceClient {
    @ApiOperation("发送验证码")
    @GetMapping("/third-part/sendCode")
    ResultBean<Void> sendCode(@RequestParam("phone") String phone,
                              @RequestParam("code") String code);

}
