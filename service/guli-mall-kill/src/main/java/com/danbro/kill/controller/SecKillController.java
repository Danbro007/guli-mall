package com.danbro.kill.controller;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.kill.service.SecKillService;
import com.danbro.kill.vo.SmsSeckillSkuRelationVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Danrbo
 * @Classname SecKillController
 * @Description TODO
 * @Date 2021/3/17 12:56
 */
@Controller
public class SecKillController {
    @Autowired
    SecKillService secKillService;

    @ResponseBody
    @ApiOperation("获取当前在秒杀的商品列表")
    @GetMapping("currentSecKillSkuList")
    public ResultBean<List<SmsSeckillSkuRelationVo>> getCurrentSecKillSkuList() throws InterruptedException {
        return ResultBean.ofSuccess(secKillService.getCurrentSecKillSkuList());
    }

    @ResponseBody
    @ApiOperation("查询要秒杀商品的秒杀信息")
    @GetMapping("seckill/info/{skuId}")
    public ResultBean<SmsSeckillSkuRelationVo> getSecKillInfo(@PathVariable Long skuId) throws InterruptedException {
        Thread.sleep(2000);
        return ResultBean.ofSuccess(secKillService.getSecKillRelationInfo(skuId));
    }

    @ApiOperation("秒杀商品")
    @GetMapping("kill")
    public String killSku(@RequestParam("killId") String killId,
                          @RequestParam("randomCode") String randomCode,
                          @RequestParam("num") Integer num,
                          Model model) {
        String orderSn = secKillService.killSku(killId, randomCode, num);
        model.addAttribute("orderSn", orderSn);
        return "success";
    }
}
