package com.danbro.cart.controller;

import com.danbro.cart.controller.dto.UserInfoDto;
import com.danbro.cart.interceptor.CartInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Classname CartController
 * @Description TODO
 * @Date 2021/3/3 19:26
 * @Created by Administrator
 */
@Controller
public class CartController {

    @GetMapping("cart.html")
    public String cartList() {
        ThreadLocal<UserInfoDto> threadLocal = CartInterceptor.threadLocal;
        UserInfoDto userInfoDto = threadLocal.get();
        System.out.println(userInfoDto);
        return "cartList";
    }
}
