package com.danbro.product.controller.front;

import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;
import com.danbro.product.controller.vo.front.SkuItemVo;
import com.danbro.product.service.PmsSkuInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Danrbo
 * @Classname ItemController
 * @Description TODO
 * @Date 2021/2/24 16:20
 */
@Controller
@RequestMapping("")
public class ItemController {

    @Resource
    PmsSkuInfoService pmsSkuInfoService;

    @GetMapping("{skuId}.html")
    public String getItem(@PathVariable Long skuId, Model model) throws ExecutionException, InterruptedException {
        SkuItemVo item = pmsSkuInfoService.getItemBySkuId(skuId);
        model.addAttribute("item", item);
        return "item";
    }
}
