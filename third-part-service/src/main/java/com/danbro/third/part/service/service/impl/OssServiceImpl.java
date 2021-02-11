package com.danbro.third.part.service.service.impl;

import cn.hutool.core.date.DateTime;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.danbro.third.part.service.service.OssService;
import com.danbro.third.part.service.utils.OssClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Classname OssServiceImpl
 * @Description TODO
 * @Date 2020/12/17 12:25
 * @Author Danrbo
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {
    private final static long EXPIRE_TIME = 30L;
    private final static long UPLOAD_FILE_MAX_SIZE = 1024 * 1024 * 10;
    private final static long UPLOAD_FILE_MIN_SIZE = 0;


    @Override
    public Map<String, String> getPolicy() {
        String endpoint = OssClientUtils.END_POINT;
        String bucket = OssClientUtils.BUCKET_NAME;
        String host = "https://" + bucket + "." + endpoint;
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
//        String callbackUrl = "http://88.88.88.88:8888";
        String date = new DateTime().toString("yyyy/MM/dd");
        Map<String, String> respMap = new LinkedHashMap<>();
        ;
        // 创建OSSClient实例。
        OSS ossClient = getOssClient();
        try {
            // 计算出超时时间
            long expireEndTime = System.currentTimeMillis() + EXPIRE_TIME * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, UPLOAD_FILE_MIN_SIZE, UPLOAD_FILE_MAX_SIZE);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, date);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap.put("accessid", OssClientUtils.ACCESS_KEY_ID);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", date);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return respMap;
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
