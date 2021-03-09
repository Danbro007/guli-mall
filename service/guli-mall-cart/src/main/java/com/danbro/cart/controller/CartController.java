package com.danbro.cart.controller;

import com.danbro.cart.controller.vo.CartItemVo;
import com.danbro.cart.controller.vo.CartVo;
import com.danbro.cart.service.CartService;
import com.danbro.common.entity.ResultBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname CartController
 * @Description TODO
 * @Date 2021/3/3 19:26
 * @Created by Administrator
 */
@Controller
public class CartController {

    @Resource
    CartService cartService;

    @GetMapping("cart.html")
    public String cartList(Model model) {
        CartVo cartVo = cartService.getCartList();
        model.addAttribute("cart", cartVo);
        return "cartList";
    }

    @GetMapping("addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num,
                            RedirectAttributes redirectAttributes) {
        cartService.addToCart(skuId, num);
        redirectAttributes.addAttribute("skuId", skuId);
        // 重定向到成功页面，防止重复添加。
        return "redirect:http://cart.gulimall.com/addToCartSuccess";
    }

    @GetMapping("addToCartSuccess")
    public String addToCartSuccess(@RequestParam("skuId") Long skuId, Model model) {
        CartItemVo cartItem = cartService.getCartItem(skuId);
        model.addAttribute("cartItem", cartItem);
        return "success";
    }

    @GetMapping("checkItem")
    public String checkItem(@RequestParam("skuId") Long skuId,
                            @RequestParam("checked") Boolean check) {
        cartService.checkItem(skuId, check);
        return "redirect:http://cart.gulimall.com/cart.html";
    }

    @GetMapping("countItem")
    public String countItem(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num) {
        cartService.countItem(skuId, num);
        return "redirect:http://cart.gulimall.com/cart.html";
    }

    @GetMapping("deleteItem")
    public String deleteItem(@RequestParam("skuId") Long skuId) {
        cartService.deleteItem(skuId);
        return "redirect:http://cart.gulimall.com/cart.html";
    }

    @ApiOperation("获取会员购物车里选中商品的信息，价格也是最新的。")
    @ResponseBody
    @GetMapping("cartList/{memberId}")
    public ResultBean<List<CartItemVo>> getCartItemList(@PathVariable Long memberId) {
        return ResultBean.ofSuccess(cartService.getCartItemList(memberId));
    }

}
