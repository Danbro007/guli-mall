package com.danbro.product.controller.front;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.danbro.product.controller.vo.PmsCategory2Vo;
import com.danbro.product.controller.vo.PmsCategoryVo;
import com.danbro.product.service.PmsCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname IndexController
 * @Description TODO
 * @Date 2021/2/17 14:16
 * @Created by Administrator
 */
@Controller
public class IndexController {

    @Resource
    PmsCategoryService pmsCategoryService;

    @GetMapping({"/", "index.html"})
    public String indexPage(Model model) {
        List<PmsCategoryVo> categoryList = pmsCategoryService.getCategoryByCatLevel(1);
        model.addAttribute("categories", categoryList);
        return "index";
    }
    @ResponseBody
    @GetMapping("index/json/catalog.json")
    public Map<String, List<PmsCategory2Vo>> getCateLogList() {
        return pmsCategoryService.getCategoryTreeFroFront();
    }
}
