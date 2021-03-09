package com.danbro.product.controller.front;

import com.danbro.product.controller.vo.OrderConfirmVo;
import com.danbro.product.service.OmsOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author Danrbo
 * @Classname OrderController
 * @Description TODO
 * @Date 2021/3/8 12:33
 */
@Controller
public class OrderController {

    @Resource
    OmsOrderService omsOrderService;

    @GetMapping("list.html")
    public String listPage() {
        return "list";
    }

    @GetMapping("detail.html")
    public String orderPage() {
        return "detail";
    }

    @GetMapping("pay.html")
    public String payPage() {
        return "pay";
    }

    @GetMapping("confirm.html")
    public String confirmPage(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmOrder = omsOrderService.createConfirmOrder();
        model.addAttribute("confirmOrder", confirmOrder);
        return "confirm";
    }
}
