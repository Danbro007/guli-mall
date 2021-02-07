package com.danbro.member.controller;

import com.danbro.member.entity.UmsGrowthChangeHistory;
import com.danbro.member.service.UmsGrowthChangeHistoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Api(tags = "成长值变化历史记录(UmsGrowthChangeHistory)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("umsGrowthChangeHistory")
public class UmsGrowthChangeHistoryController {
    @Autowired
    private  UmsGrowthChangeHistoryService umsGrowthChangeHistoryService;
 
}