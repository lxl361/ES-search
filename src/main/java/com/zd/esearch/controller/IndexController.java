package com.zd.esearch.controller;

import com.zd.esearch.dto.PaginationDTO;
import com.zd.esearch.mapper.UserMapper;
import com.zd.esearch.model.User;
import com.zd.esearch.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
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
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "3") Integer size){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&& cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
            PaginationDTO pagination=questionService.list(page,size);
            //System.out.println("pa------->"+pagination);
            model.addAttribute("pagination",pagination);
        }
        return "index";
    }


}
