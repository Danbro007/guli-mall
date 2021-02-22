package com.danbro.search.controller.front;

import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import com.danbro.search.service.SearchService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @Classname IndexController
 * @Description TODO
 * @Date 2021/2/20 11:20
 * @Created by Administrator
 */
@Controller
public class IndexController {
    @Autowired
    SearchService searchService;

    @GetMapping("list.html")
    public String list(SearchParamVo searchParamVo, Model model) {
        SearchResponseVo responseVo = searchService.search(searchParamVo);
        model.addAttribute("result", responseVo);
        return "list";
    }
}
