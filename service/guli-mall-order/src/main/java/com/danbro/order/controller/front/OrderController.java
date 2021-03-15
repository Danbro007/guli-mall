package com.danbro.order.controller.front;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.danbro.common.enums.PageParam;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.utils.Pagination;
import com.danbro.order.config.AlipayTemplate;
import com.danbro.order.controller.vo.OmsOrderVo;
import com.danbro.order.controller.vo.OrderConfirmVo;
import com.danbro.order.controller.vo.OrderToResponseVo;
import com.danbro.order.controller.vo.PayAsyncVo;
import com.danbro.order.controller.vo.PayVo;
import com.danbro.order.controller.vo.SubmitOrderVo;
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

    @ApiOperation("支付宝支付成功的回调函数")
    @ResponseBody
    @PostMapping("/payed/notify")
    public String aliPayCallback(PayAsyncVo payAsyncVo, HttpServletRequest request) throws AlipayApiException {
        System.out.println("收到支付宝异步通知******************");
        // 只要收到支付宝的异步通知，返回 success 支付宝便不再通知
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayTemplate.getAlipay_public_key(),
                alipayTemplate.getCharset(), alipayTemplate.getSign_type()); //调用SDK验证签名

        if (signVerified) {
            System.out.println("支付宝异步通知验签成功");
            //修改订单状态
            omsOrderService.handlerPayResult(payAsyncVo);
            return "success";
        } else {
            System.out.println("支付宝异步通知验签失败");
            return "error";
        }
    }
}
