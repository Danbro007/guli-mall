package com.danbro.coupon.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.coupon.controller.vo.SmsSeckillPromotionVo;
import com.danbro.coupon.entity.SmsSeckillPromotion;
import com.danbro.coupon.service.SmsSeckillPromotionService;
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
@Api(tags = "秒杀活动(SmsSeckillPromotion)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("coupon/seckillpromotion")
public class SmsSeckillPromotionController {
    @Autowired
    private SmsSeckillPromotionService smsSeckillPromotionService;

    @GetMapping("list")
    public ResultPageBean<SmsSeckillPromotionVo, SmsSeckillPromotion> queryPageSecKillPromotion(@RequestParam("page") Long page,
                                                                                                @RequestParam("limit") Long limit,
                                                                                                @RequestParam(value = "key", required = false) String key) {
        PageParam<SmsSeckillPromotion> pageParam = new PageParam<SmsSeckillPromotion>().setPage(page).setLimit(limit);
        return ResultPageBean.ofSuccess(smsSeckillPromotionService.queryPage(pageParam, key));
    }

    @PostMapping()
    public ResultBean<SmsSeckillPromotionVo> insertSeckillPromotion(@Validated(Insert.class) @RequestBody SmsSeckillPromotionVo smsSeckillPromotionVo) {
        return ResultBean.ofSuccess(smsSeckillPromotionService.insert(smsSeckillPromotionVo));
    }

    @PutMapping()
    public ResultBean<SmsSeckillPromotionVo> updateSeckillPromotion(@Validated(Update.class) @RequestBody SmsSeckillPromotionVo smsSeckillPromotionVo) {
        return ResultBean.ofSuccess(smsSeckillPromotionService.update(smsSeckillPromotionVo));
    }

    @DeleteMapping()
    public ResultBean<Void> batchDeleteSeckillPromotion(Long[] Ids) {
        smsSeckillPromotionService.batchDeletePromotion(Ids);
        return ResultBean.ofSuccess();
    }
}