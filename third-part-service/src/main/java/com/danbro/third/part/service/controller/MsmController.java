package com.danbro.third.part.service.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.third.part.service.service.MsgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname LoginController
 * @Description TODO 短信服务
 * @Date 2021/2/25 17:56
 * @Created by Administrator
 */
@RestController
@RequestMapping("/sms")
public class MsmController {

    @Autowired
    MsgService msgService;

    @ApiOperation("发送验证码")
    @GetMapping("sendCode")
    public ResultBean<Void> sendCode(@RequestParam String phone) {
        msgService.sendCode(phone);
        return ResultBean.ofSuccess();
    }

}
