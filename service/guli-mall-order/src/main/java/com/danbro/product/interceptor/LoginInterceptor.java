package com.danbro.product.interceptor;

import com.danbro.common.dto.UmsMemberVo;
import com.danbro.common.utils.MyObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Danrbo
 * @Classname LoginInterceptor
 * @Description TODO 登录拦截器
 * @Date 2021/3/9 11:03
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    public static final String LOGIN_USER = "loginUser";
    public static ThreadLocal<UmsMemberVo> MEMBER_THREADLOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UmsMemberVo memberVo = (UmsMemberVo) request.getSession().getAttribute(LOGIN_USER);
        if (MyObjectUtils.isNotNull(memberVo)) {
            MEMBER_THREADLOCAL.set(memberVo);
            return true;
        } else {
            request.getSession().setAttribute("msg", "请先登录！");
            response.sendRedirect("http://auth.gulimall.com/login.html");
            return false;
        }
    }
}
