package com.danbro.third.part.service.controller;

import com.danbro.service.base.entity.ResultBean;
import com.danbro.third.part.service.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @Classname OssController
 * @Description TODO
 * @Date 2020/12/17 12:37
 * @Author Danrbo
 */
@RestController
@RequestMapping("third-part/oss")
@Slf4j
@Api(tags = "oss上传服务")
public class OssController {

    @Resource
    OssService ossService;

    @ApiOperation("获取服务端签名数据")
    @GetMapping("/policy")
    public ResultBean<Map<String, String>> getPolicy() throws IOException {
        return ResultBean.ofSuccess(ossService.getPolicy());
    }
}
