package com.danbro.service.base.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.enums.ResponseCode;
import com.danbro.common.utils.MyJSONUtils;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname SentinelConfig
 * @Description TODO 自定义返回流量控制的返回内容
 * @Date 2021/4/5 20:25
 * @Created by Administrator
 */
@Configuration
public class SentinelConfig {
    public SentinelConfig() {
        WebCallbackManager.setUrlBlockHandler((httpServletRequest, httpServletResponse, e) -> {
            ResultBean<Object> resultBean = ResultBean.ofFailure(ResponseCode.TOO_MANY_FLOW);
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().write(MyJSONUtils.toJSONString(resultBean));
        });
    }
}
