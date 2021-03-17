package com.danbro.coupon.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.coupon.controller.vo.SmsSeckillSessionVo;
import com.danbro.coupon.entity.SmsSeckillSession;
import com.danbro.coupon.service.SmsSecKillSessionService;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "秒杀活动场次(SmsSeckillSession)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("coupon/seckillsession")
public class SmsSecKillSessionController {
    @Autowired
    private SmsSecKillSessionService smsSecKillSessionService;

    @GetMapping("list")
    public ResultPageBean<SmsSeckillSessionVo, SmsSeckillSession> queryPageSecKillPromotion(@RequestParam("page") Long page,
                                                                                            @RequestParam("limit") Long limit,
                                                                                            @RequestParam(value = "key", required = false) String key) {
        PageParam<SmsSeckillSession> pageParam = new PageParam<SmsSeckillSession>().setPage(page).setLimit(limit);
        return ResultPageBean.ofSuccess(smsSecKillSessionService.queryPage(pageParam, key));
    }

    @PostMapping()
    public ResultBean<SmsSeckillSessionVo> insertSecKillSession(@Validated(Insert.class) @RequestBody SmsSeckillSessionVo secKillSessionVo) {
        return ResultBean.ofSuccess(smsSecKillSessionService.insert(secKillSessionVo));
    }

    @PutMapping()
    public ResultBean<SmsSeckillSessionVo> updateSecKillSession(@Validated(Update.class) @RequestBody SmsSeckillSessionVo secKillSessionVo) {
        return ResultBean.ofSuccess(smsSecKillSessionService.update(secKillSessionVo));
    }

    @DeleteMapping()
    public ResultBean<Void> batchDeleteSecKillSession(@RequestBody Long[] Ids) {
        smsSecKillSessionService.batchDeleteSession(Ids);
        return ResultBean.ofSuccess();
    }

    @GetMapping("info/{id}")
    public ResultBean<SmsSeckillSessionVo> getSecKillSessionInfo(@PathVariable Long id) {
        return ResultBean.ofSuccess(smsSecKillSessionService.getInfoById(id));
    }

    @GetMapping("last/3")
    public ResultBean<List<SmsSeckillSessionVo>> getLast3DaysSku() {
        return ResultBean.ofSuccess(smsSecKillSessionService.getLast3DaySku());
    }
}