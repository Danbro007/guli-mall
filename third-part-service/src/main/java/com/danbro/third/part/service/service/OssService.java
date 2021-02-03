package com.danbro.third.part.service.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Classname OssService
 * @Description TODO
 * @Date 2020/12/17 12:23
 * @Author Danrbo
 */

public interface OssService {

    /**
     * 上传图片
     *
     * @param file 图片文件
     * @return 上传后图片的URL
     */
    String uploadImage(MultipartFile file, String type) throws IOException;
}
