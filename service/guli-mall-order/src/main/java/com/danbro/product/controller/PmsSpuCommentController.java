package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsSpuComment;
import com.danbro.product.service.PmsSpuCommentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:43
 */
@Api(tags = "商品评价(PmsSpuComment)") 
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsSpuComment")
public class PmsSpuCommentController {
    @Autowired
    private  PmsSpuCommentService pmsSpuCommentService;
 
}