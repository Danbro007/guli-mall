package com.danbro.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Classname LoginController
 * @Description TODO
 * @Date 2021/2/25 17:56
 * @Created by Administrator
 */
@Controller
public class LoginController {


    @GetMapping("login.html")
    public String loginPage() {
        return "login";
    }
}
