package com.danbro.member.controller;
 
import com.danbro.member.entity.UmsMemberCollectSubject;
import com.danbro.member.service.UmsMemberCollectSubjectService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Api(tags = "会员收藏的专题活动(UmsMemberCollectSubject)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("umsMemberCollectSubject")
public class UmsMemberCollectSubjectController {
    @Autowired
    private  UmsMemberCollectSubjectService umsMemberCollectSubjectService;
 
}