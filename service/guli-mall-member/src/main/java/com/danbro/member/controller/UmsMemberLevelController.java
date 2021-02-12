package com.danbro.member.controller;

import com.danbro.common.entity.ResultBean;
import com.danbro.common.entity.ResultPageBean;
import com.danbro.common.enums.PageParam;
import com.danbro.member.controller.vo.UmsMemberLevelVo;
import com.danbro.member.entity.UmsMemberLevel;
import com.danbro.member.service.UmsMemberLevelService;
import com.danbro.service.common.validtors.groups.Insert;
import com.danbro.service.common.validtors.groups.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Api(tags = "会员等级(UmsMemberLevel)")
@Validated
@RestController
@AllArgsConstructor
@Setter
@RequestMapping("/member/memberlevel")
public class UmsMemberLevelController {

    private UmsMemberLevelService umsMemberLevelService;

    @ApiOperation("分页查询会员等级")
    @GetMapping("list")
    public ResultPageBean<UmsMemberLevelVo, UmsMemberLevel> getMemberLevelList(@RequestParam("page") Long page,
                                                                               @RequestParam("limit") Long limit,
                                                                               @RequestParam(value = "key", required = false) String key) {
        PageParam<UmsMemberLevel> pageParam = new PageParam<UmsMemberLevel>().setLimit(limit).setPage(page);
        return ResultPageBean.ofSuccess(umsMemberLevelService.getMemberLevelList(pageParam, key));
    }

    @ApiOperation("添加会员等级")
    @PostMapping()
    public ResultBean<UmsMemberLevelVo> insertMemberLevel(@Validated(Insert.class) @RequestBody UmsMemberLevelVo memberLevelVo) {
        return ResultBean.ofSuccess(umsMemberLevelService.insertMemberLevel(memberLevelVo));
    }

    @ApiOperation("修改会员等级")
    @PutMapping()
    public ResultBean<UmsMemberLevelVo> updateMemberLevel(@Validated(Update.class) @RequestBody UmsMemberLevelVo memberLevelVo) {
        return ResultBean.ofSuccess(umsMemberLevelService.updateMemberLevel(memberLevelVo));
    }

    @ApiOperation("获取会员等级的详细信息")
    @GetMapping("info/{memberLevelId}")
    public ResultBean<UmsMemberLevelVo> getMemberLevelInfo(@PathVariable Long memberLevelId){
        return ResultBean.ofSuccess(umsMemberLevelService.getMemberLevelInfoById(memberLevelId));
    }
    @ApiOperation("通过会员等级名获取相信信息")
    @GetMapping("info/{memberLevelName}")
    public ResultBean<UmsMemberLevelVo> getMemberLevelInfoByName(@PathVariable String memberLevelName){
        return ResultBean.ofSuccess(umsMemberLevelService.getMemberLevelInfoByName(memberLevelName));

    }
}