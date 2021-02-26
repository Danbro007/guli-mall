package com.danbro.third.part.service.components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname MsgProperties
 * @Description TODO 短信服务组件
 * @Date 2021/2/25 19:25
 * @Created by Administrator
 */
@Component
@Data
@ConfigurationProperties(prefix = "message")
public class MsgComponent {
    /**
     * 短信接口
     */
    private String host;

    private String path;
    /**
     * 短信模块的appCode
     */
    private String appCode;
    /**
     * 短信模板类型
     */
    private String skin;
    private String sign;

    /**
     * 发送验证码
     */
    public void sendCode(String code, String phone) {
        // 【5】拼接请求链接
        String urlSend = String.format("%s%s?param=%s&phone=%s&sign=%s&skin=%s", host, path, code, phone, sign, skin);
        try {
            URL url = new URL(urlSend);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // 格式Authorization:APPCODE (中间是英文空格)
            urlConnection.setRequestProperty("Authorization", "APPCODE " + appCode);
            int httpCode = urlConnection.getResponseCode();
            if (httpCode == 200) {
                String json = read(urlConnection.getInputStream());
                System.out.println("正常请求计费(其他均不计费)");
                System.out.println("获取返回的json:");
                System.out.print(json);
            } else {
                Map<String, List<String>> map = urlConnection.getHeaderFields();
                String error = map.get("X-Ca-Error-Message").get(0);
                if (httpCode == 400 && "Invalid AppCode `not exists`".equals(error)) {
                    System.out.println("AppCode错误 ");
                } else if (httpCode == 400 && "Invalid Url".equals(error)) {
                    System.out.println("请求的 Method、Path 或者环境错误");
                } else if (httpCode == 400 && "Invalid Param Location".equals(error)) {
                    System.out.println("参数错误");
                } else if (httpCode == 403 && "Unauthorized".equals(error)) {
                    System.out.println("服务未被授权（或URL和Path不正确）");
                } else if (httpCode == 403 && "Quota Exhausted".equals(error)) {
                    System.out.println("套餐包次数用完 ");
                } else {
                    System.out.println("参数名错误 或 其他错误");
                    System.out.println(error);
                }
            }

        } catch (MalformedURLException e) {
            System.out.println("URL格式错误");
        } catch (UnknownHostException e) {
            System.out.println("URL地址错误");
        } catch (Exception e) {
            // 打开注释查看详细报错异常信息
            // e.printStackTrace();
        }


    }

    /**
     * 读取返回结果
     * @param is
     * @return
     * @throws IOException
     */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), StandardCharsets.UTF_8);
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

}
