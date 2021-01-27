package com.danbro.member.controller;
 
import com.danbro.member.entity.UmsMemberReceiveAddress;
import com.danbro.member.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:58
 */
@Api(tags = "会员收货地址(UmsMemberReceiveAddress)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("umsMemberReceiveAddress")
public class UmsMemberReceiveAddressController {
    @Autowired
    private  UmsMemberReceiveAddressService umsMemberReceiveAddressService;
 
}