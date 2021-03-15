package com.danbro.order.controller.front;


import com.alipay.api.AlipayApiException;
import com.danbro.common.enums.PageParam;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.Pagination;
import com.danbro.order.config.AlipayTemplate;
import com.danbro.order.controller.vo.*;
import com.danbro.order.entity.OmsOrder;
import com.danbro.order.service.OmsOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    AlipayTemplate alipayTemplate;

    @ApiOperation("分页查询出当前用户的订单列表")
    @GetMapping("orderList")
    public String queryPageOrderList(@RequestParam(value = "page", defaultValue = "1") Long page,
                                     @RequestParam(value = "limit", defaultValue = "10") Long limit,
                                     @RequestParam(value = "key", required = false) String key,
                                     Model model) {
        PageParam<OmsOrder> pageParam = new PageParam<OmsOrder>().setPage(page).setLimit(limit);
        Pagination<OmsOrderVo, OmsOrder> pagination = omsOrderService.queryPageOrder(pageParam, key);
        model.addAttribute("Pagination", pagination);
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

    @ResponseBody
    @ApiOperation("支付宝支付页面")
    @GetMapping(value = "aliPayOrder", produces = "text/html")
    public String aliPayPage(@RequestParam("orderSn") String orderSn) throws AlipayApiException {
        PayVo payVo = omsOrderService.getPayInfoByOrderSn(orderSn);
        return alipayTemplate.pay(payVo);
    }
}
