package com.danbro.third.part.service.service;


import java.io.IOException;
import java.util.Map;

/**
 * @Classname OssService
 * @Description TODO
 * @Date 2020/12/17 12:23
 * @Author Danrbo
 */

public interface OssService {

    /**
     * 获取上传凭证
     *
     * @throws IOException
     */
    Map<String, String> getPolicy() throws IOException;
}
