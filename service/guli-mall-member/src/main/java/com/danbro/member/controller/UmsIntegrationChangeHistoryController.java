package com.danbro.member.controller;
 
import com.danbro.member.entity.UmsIntegrationChangeHistory;
import com.danbro.member.service.UmsIntegrationChangeHistoryService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Api(tags = "积分变化历史记录(UmsIntegrationChangeHistory)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("umsIntegrationChangeHistory")
public class UmsIntegrationChangeHistoryController {
    @Autowired
    private  UmsIntegrationChangeHistoryService umsIntegrationChangeHistoryService;
 
}