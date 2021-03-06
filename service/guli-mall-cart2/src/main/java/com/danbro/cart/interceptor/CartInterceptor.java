package com.danbro.cart.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.danbro.cart.controller.dto.UserInfoDto;
import com.danbro.common.dto.UmsMemberVo;
import com.danbro.common.utils.MyObjectUtils;
import com.danbro.common.utils.MyRandomUtils;
import com.danbro.common.utils.MyStrUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname CartInterceptor
 * @Description TODO 购物车拦截器
 * @Date 2021/3/3 20:39
 * @Created by Administrator
 */
@Component
public class CartInterceptor implements HandlerInterceptor {
    public static final String LOGIN_USER = "loginUser";
    public static final String USER_KEY = "user-key";
    public static final String DOMAIN = "gulimall.com";
    public static int COOKIE_TIME_OUT = 60 * 60 * 24 * 30;
    public static ThreadLocal<UserInfoDto> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserInfoDto userInfoDto = new UserInfoDto();
        UmsMemberVo memberDto = (UmsMemberVo) session.getAttribute(LOGIN_USER);
        // 用户已登录
        if (MyObjectUtils.isNotNull(memberDto)) {
            userInfoDto.setId(memberDto.getId());
            // 设置为非零时用户
            userInfoDto.setTempUser(false);
        }
        // 查看有没有 user-key 的 cookie
        Cookie[] cookies = request.getCookies();
        if (MyObjectUtils.isNotNull(cookies) && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (USER_KEY.equals(cookie.getName())) {
                    userInfoDto.setUserKey(cookie.getValue());
                }
            }
        }
        // 放入 threadLocal 里
        threadLocal.set(userInfoDto);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserInfoDto userInfoDto = threadLocal.get();
        // 是临时用户并且 userKey 为空则创建一个 userKey 并放入 cookie 里
        if (MyObjectUtils.isNotNull(userInfoDto) && userInfoDto.getTempUser() && MyStrUtils.isEmpty(userInfoDto.getUserKey())) {
            String randomUUID = MyRandomUtils.randomUUID();
            Cookie cookie = new Cookie(USER_KEY, randomUUID);
            cookie.setDomain(DOMAIN);
            cookie.setMaxAge(COOKIE_TIME_OUT);
            response.addCookie(cookie);
        }
    }
}
