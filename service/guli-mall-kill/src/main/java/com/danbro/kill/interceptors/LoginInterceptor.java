package com.danbro.kill.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.danbro.common.dto.UmsMemberVo;
import com.danbro.common.utils.MyObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author Danrbo
 * @Classname LoginInterceptor
 * @Description TODO 登录拦截器
 * @Date 2021/3/9 11:03
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    public static final String LOGIN_USER = "loginUser";
    public static ThreadLocal<UmsMemberVo> MEMBER_THREADED = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        // 拦截秒杀请求，要先登录
        boolean match = new AntPathMatcher().match("/kill", requestURI);
        if (match) {
            UmsMemberVo memberVo = (UmsMemberVo) request.getSession().getAttribute(LOGIN_USER);
            if (MyObjectUtils.isNotNull(memberVo)) {
                try {
                    MEMBER_THREADED.set(memberVo);
                } catch (Exception e) {
                    MEMBER_THREADED.remove();
                }
                return true;
            } else {
                request.getSession().setAttribute("msg", "请先登录！");
                response.sendRedirect("http://auth.gulimall.com/login.html");
                return false;
            }
        }
        return true;
    }
}