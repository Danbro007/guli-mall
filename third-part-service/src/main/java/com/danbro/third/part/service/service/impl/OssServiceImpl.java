package com.danbro.third.part.service.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.danbro.third.part.service.service.OssService;
import com.danbro.third.part.service.utils.OssClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Classname OssServiceImpl
 * @Description TODO
 * @Date 2020/12/17 12:25
 * @Author Danrbo
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {

    @Override
    public String uploadImage(MultipartFile file, String type) throws IOException {
        OSS ossClient = getOssClient();
        String uploadFileName = OssClientUtils.getUploadFileName(file.getOriginalFilename(), type);
        ossClient.putObject(OssClientUtils.BUCKET_NAME, uploadFileName, file.getInputStream());
        return uploadFileName;
    }

    /**
     * 创建一个 oss 客户端
     *
     * @return oss 客户端
     */
    private OSS getOssClient() {
        return new OSSClientBuilder().build(OssClientUtils.END_POINT, OssClientUtils.ACCESS_KEY_ID, OssClientUtils.ACCESS_KEY_SECRET);
    }
}
