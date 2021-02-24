package com.danbro.search.controller.front;

import com.danbro.search.controller.vo.SearchParamVo;
import com.danbro.search.controller.vo.SearchResponseVo;
import com.danbro.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

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
    public String list(SearchParamVo searchParamVo, Model model, HttpServletRequest request) {
        SearchResponseVo responseVo = searchService.search(searchParamVo, request);
        model.addAttribute("result", responseVo);
        return "list";
    }
}
