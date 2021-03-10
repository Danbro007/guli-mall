package com.danbro.order.controller.front;


import com.danbro.common.exceptions.GuliMallException;
import com.danbro.order.controller.vo.OrderConfirmVo;
import com.danbro.order.controller.vo.OrderToResponseVo;
import com.danbro.order.controller.vo.SubmitOrderVo;
import com.danbro.order.service.OmsOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author Danrbo
 * @Classname OrderController
 * @Description TODO 订单controller
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

    @GetMapping("pay")
    public String payPage() {
        return "pay";
    }

    @GetMapping("confirm.html")
    public String confirmPage(Model model) throws ExecutionException, InterruptedException {
        OrderConfirmVo confirmOrder = omsOrderService.createConfirmOrder();
        model.addAttribute("confirmOrder", confirmOrder);
        return "confirm";
    }

    @ApiOperation("提交订单")
    @PostMapping("submitOrder")
    public String submitOrder(SubmitOrderVo orderVo, RedirectAttributes redirectAttributes, Model model) {
        try {
            OrderToResponseVo responseVo = omsOrderService.createOrder(orderVo);
            model.addAttribute("submitOrderResp", responseVo);
            return "pay";
        } catch (GuliMallException e) {
            redirectAttributes.addAttribute("msg", e.getMessage());
            return "redirect:http://order.gulimall.com/confirm.html";
        }
    }
}
