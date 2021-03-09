package com.danbro.product.controller.front;

import com.danbro.common.entity.ResultBean;
import com.danbro.product.controller.vo.OrderConfirmVo;
import com.danbro.product.controller.vo.SubmitOrderVo;
import com.danbro.product.service.OmsOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @ApiOperation("提交订单")
    @PostMapping("submitOrder")
    public ResultBean<Void> submitOrder(SubmitOrderVo orderVo) {
        omsOrderService.createOrder(orderVo);
        return ResultBean.ofSuccess();
    }
}
