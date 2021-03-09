package com.danbro.product.controller.admin;

import com.danbro.product.service.PmsSpuImagesService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author makejava
 * @since 2021-01-28 18:56:55
 */
@Api(tags = "spu图片(PmsSpuImages)")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("pmsSpuImages")
public class PmsSpuImagesController {
    @Autowired
    private  PmsSpuImagesService pmsSpuImagesService;
 
}