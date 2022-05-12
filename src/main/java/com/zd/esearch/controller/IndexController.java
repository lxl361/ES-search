package com.zd.esearch.controller;

import com.github.pagehelper.PageInfo;
import com.zd.esearch.dto.PaginationDTO;
import com.zd.esearch.dto.QuestionDTO;
import com.zd.esearch.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: 首页
 * @date 2021/12/26 0026 14:32
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "3") Integer size){
            PageInfo<QuestionDTO> list=questionService.list(page,size);
        PageInfo<QuestionDTO> pagination = new PageInfo<>(list.getList());
        model.addAttribute("list",list.getList());
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
