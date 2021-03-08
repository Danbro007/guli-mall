package com.danbro.order.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Danrbo
 * @Classname OrderController
 * @Description TODO
 * @Date 2021/3/8 12:33
 */
@Controller
public class OrderController {
    @GetMapping("list.html")
    public String listPage() {
        return "list";
    }
}
