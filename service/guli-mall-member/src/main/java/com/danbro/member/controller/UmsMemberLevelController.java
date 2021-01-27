package com.danbro.member.controller;
 
import com.danbro.member.entity.UmsMemberLevel;
import com.danbro.member.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:58
 */
@Api(tags = "会员等级(UmsMemberLevel)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("umsMemberLevel")
public class UmsMemberLevelController {
    @Autowired
    private  UmsMemberLevelService umsMemberLevelService;
 
}