package com.danbro.member.controller;
 
import com.danbro.member.entity.UmsMemberLoginLog;
import com.danbro.member.service.UmsMemberLoginLogService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Api(tags = "会员登录记录(UmsMemberLoginLog)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("umsMemberLoginLog")
public class UmsMemberLoginLogController {
    @Autowired
    private  UmsMemberLoginLogService umsMemberLoginLogService;
 
}