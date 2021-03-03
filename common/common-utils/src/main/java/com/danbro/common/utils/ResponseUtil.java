package com.danbro.common.utils;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import com.danbro.common.entity.ResultBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ResponseUtil {

    public static void out(HttpServletResponse response, ResultBean r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
