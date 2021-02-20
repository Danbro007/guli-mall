package com.danbro.search.controller.front;

import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import com.danbro.search.service.impl.SearchService;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Classname IndexController
 * @Description TODO
 * @Date 2021/2/20 11:20
 * @Created by Administrator
 */
@Controller
@Setter
public class IndexController {

    SearchService searchService;

    @GetMapping("list.html")
    public String list() {
        return "list";
    }

    @GetMapping("")
    public String search(SearchParamVo searchParamVo, Model model) {
        SearchResponseVo responseVo = searchService.search(searchParamVo);
        model.addAttribute("result", responseVo);
        return "list";
    }

}
