package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsCommentReplay;
import com.danbro.product.service.PmsCommentReplayService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Api(tags = "商品评价回复关系(PmsCommentReplay)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsCommentReplay")
public class PmsCommentReplayController {
    @Autowired
    private  PmsCommentReplayService pmsCommentReplayService;
 
}