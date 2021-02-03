package com.danbro.third.part.service.controller;

import com.danbro.service.base.entity.ResultBean;
import com.danbro.third.part.service.service.OssService;
import com.danbro.third.part.service.utils.OssClientUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

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

    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "image", value = "文件流对象,接收数组格式", required = true, dataType = "MultipartFile"),
                    @ApiImplicitParam(name = "type", value = "title", required = true)
            }
    )
    @ApiOperation("上传图片")
    @PostMapping("image/{type}")
    public ResultBean<String> uploadImage(@RequestParam(value = "image", required = true) MultipartFile image, @PathVariable String type) throws IOException {
        String imageUrl = ossService.uploadImage(image, type);
        return ResultBean.ofSuccess(String.format("https://%s.%s/%s",
                OssClientUtils.BUCKET_NAME, OssClientUtils.END_POINT, imageUrl));
    }
}
