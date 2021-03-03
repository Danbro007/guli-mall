package com.danbro.cart.controller;

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

    @GetMapping("cartList")
    public String cartList() {
        return "cartList";
    }
}
