package com.danbro.product.controller;
 
import com.danbro.product.entity.PmsSpuImages;
import com.danbro.product.service.PmsSpuImagesService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
 
 
/**
 * @author makejava
 * @since 2021-01-27 21:30:43
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