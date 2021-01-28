package com.danbro.member.controller;
 
import com.danbro.member.entity.UmsMember;
import com.danbro.member.service.UmsMemberService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Api(tags = "会员(UmsMember)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("umsMember")
public class UmsMemberController {
    @Autowired
    private  UmsMemberService umsMemberService;
 
}