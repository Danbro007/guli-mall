package com.danbro.seckill.controller;

import java.util.List;
import com.danbro.common.entity.ResultBean;
import com.danbro.seckill.service.SecKillService;
import com.danbro.seckill.vo.SmsSeckillSkuRelationVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Danrbo
 * @Classname SecKillController
 * @Description TODO
 * @Date 2021/3/17 12:56
 */
@RestController
public class SecKillController {
    @Autowired
    SecKillService secKillService;

    @ApiOperation("获取当前在秒杀的商品列表")
    @GetMapping("currentSecKillSkuList")
    public ResultBean<List<SmsSeckillSkuRelationVo>> getCurrentSecKillSkuList() {
        return ResultBean.ofSuccess(secKillService.getCurrentSecKillSkuList());
    }

    @ApiOperation("查询要秒杀商品的秒杀信息")
    @GetMapping("seckill/info/{skuId}")
    public ResultBean<SmsSeckillSkuRelationVo> getSecKillInfo(@PathVariable Long skuId) {
        return ResultBean.ofSuccess(secKillService.getSecKillRelationInfo(skuId));
    }

    @ApiOperation("秒杀商品")
    @GetMapping("kill")
    public ResultBean<String> killSku(@RequestParam("killId") String killId,
                                      @RequestParam("randomCode") String randomCode,
                                      @RequestParam("num") Integer num) {
        return ResultBean.ofSuccess(secKillService.killSku(killId, randomCode, num));
    }
}
