package com.danbro.member.controller;
 
import com.danbro.member.entity.UmsMemberCollectSpu;
import com.danbro.member.service.UmsMemberCollectSpuService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-28 19:03:18
 */
@Api(tags = "会员收藏的商品(UmsMemberCollectSpu)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("umsMemberCollectSpu")
public class UmsMemberCollectSpuController {
    @Autowired
    private  UmsMemberCollectSpuService umsMemberCollectSpuService;
 
}