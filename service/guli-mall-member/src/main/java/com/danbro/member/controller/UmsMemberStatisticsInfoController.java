package com.danbro.member.controller;
 
import com.danbro.member.entity.UmsMemberStatisticsInfo;
import com.danbro.member.service.UmsMemberStatisticsInfoService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:58
 */
@Api(tags = "会员统计信息(UmsMemberStatisticsInfo)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("umsMemberStatisticsInfo")
public class UmsMemberStatisticsInfoController {
    @Autowired
    private  UmsMemberStatisticsInfoService umsMemberStatisticsInfoService;
 
}