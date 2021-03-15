package com.danbro.coupon.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.coupon.controller.vo.SmsSeckillSessionVo;
import com.danbro.coupon.entity.SmsSeckillSession;
import com.danbro.coupon.service.SmsSeckillSessionService;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:09:33
 */
@Api(tags = "秒杀活动场次(SmsSeckillSession)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("coupon/seckillsession")
public class SmsSeckillSessionController {
    @Autowired
    private SmsSeckillSessionService smsSeckillSessionService;

    @GetMapping("list")
    public ResultPageBean<SmsSeckillSessionVo, SmsSeckillSession> queryPageSecKillPromotion(@RequestParam("page") Long page,
                                                                                            @RequestParam("limit") Long limit,
                                                                                            @RequestParam(value = "key", required = false) String key) {
        PageParam<SmsSeckillSession> pageParam = new PageParam<SmsSeckillSession>().setPage(page).setLimit(limit);
        return ResultPageBean.ofSuccess(smsSeckillSessionService.queryPage(pageParam, key));
    }

    @PostMapping()
    public ResultBean<SmsSeckillSessionVo> insertSeckillSession(@Validated(Insert.class) @RequestBody SmsSeckillSessionVo seckillSessionVo) {
        return ResultBean.ofSuccess(smsSeckillSessionService.insert(seckillSessionVo));
    }

    @PutMapping()
    public ResultBean<SmsSeckillSessionVo> updateSeckillSession(@Validated(Update.class) @RequestBody SmsSeckillSessionVo seckillSessionVo) {
        return ResultBean.ofSuccess(smsSeckillSessionService.update(seckillSessionVo));
    }

    @DeleteMapping()
    public ResultBean<Void> batchDeleteSeckillSession(Long[] Ids) {
        smsSeckillSessionService.batchDeleteSession(Ids);
        return ResultBean.ofSuccess();
    }

    @GetMapping("info/{id}")
    public ResultBean<SmsSeckillSessionVo> getSedckillSessionInfo(@PathVariable Long id) {
        return ResultBean.ofSuccess(smsSeckillSessionService.getInfoById(id));
    }
}