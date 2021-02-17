package com.danbro.product.controller.admin;

import com.danbro.product.service.PmsSpuCommentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author makejava
 * @since 2021-01-28 18:56:54
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