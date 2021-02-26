package com.danbro.third.part.service.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @Classname OssClientUtils
 * @Description TODO
 * @Date 2020/12/17 12:57
 * @Author Danrbo
 */
@Component
public class OssClientUtils implements InitializingBean {
    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;

    @Value("${aliyun.oss.file.keyid}")
    private String accessKeyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static long UPLOAD_IMAGE_SIZE_LIMIT = 1024 * 1024 * 2;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endPoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
    }

    /**
     * 获取要上传文件的文件路径
     *
     * @param filename   文件名
     * @param uploadType 文件类型
     * @return 文件路径
     */
    public static String getUploadFileName(String filename, String uploadType) {
        String uuid = UUID.randomUUID().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String formatTime = now.format(formatter);
        return String.format("%s/%s/%s-%s", uploadType, formatTime, uuid.replace("-", ""), filename);
    }
}
