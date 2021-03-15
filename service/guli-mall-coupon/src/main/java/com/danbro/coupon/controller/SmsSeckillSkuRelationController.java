package com.danbro.coupon.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.coupon.controller.vo.SmsSeckillSkuRelationVo;
import com.danbro.coupon.entity.SmsSeckillSkuRelation;
import com.danbro.coupon.service.SmsSeckillSkuRelationService;
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
@Api(tags = "秒杀活动商品关联(SmsSeckillSkuRelation)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/coupon/seckillskurelation")
public class SmsSeckillSkuRelationController {
    @Autowired
    private SmsSeckillSkuRelationService smsSeckillSkuRelationService;

    @GetMapping("list")
    public ResultPageBean<SmsSeckillSkuRelationVo, SmsSeckillSkuRelation> queryPageSkuRelation(@RequestParam("page") Long page,
                                                                                               @RequestParam("limit") Long limit,
                                                                                               @RequestParam(value = "key", required = false) String key) {
        PageParam<SmsSeckillSkuRelation> pageParam = new PageParam<SmsSeckillSkuRelation>().setPage(page).setLimit(limit);
        return ResultPageBean.ofSuccess(smsSeckillSkuRelationService.queryPage(pageParam, key));
    }

    @PostMapping()
    public ResultBean<SmsSeckillSkuRelationVo> insertSkuRelation(@Validated(Insert.class) @RequestBody SmsSeckillSkuRelationVo skuRelationVo) {
        return ResultBean.ofSuccess(smsSeckillSkuRelationService.insert(skuRelationVo));
    }

    @PutMapping()
    public ResultBean<SmsSeckillSkuRelationVo> updateSkuRelation(@Validated(Update.class) @RequestBody SmsSeckillSkuRelationVo skuRelationVo) {
        return ResultBean.ofSuccess(smsSeckillSkuRelationService.update(skuRelationVo));
    }

    @DeleteMapping()
    public ResultBean<Void> batchDeleteSkuRelation(Long[] Ids) {
        smsSeckillSkuRelationService.batchDeleteSkuRelation(Ids);
        return ResultBean.ofSuccess();
    }

    @GetMapping("info/{id}")
    public ResultBean<SmsSeckillSkuRelationVo> getSkuRelationInfo(@PathVariable Long id) {
        return ResultBean.ofSuccess(smsSeckillSkuRelationService.getInfoById(id));
    }
}