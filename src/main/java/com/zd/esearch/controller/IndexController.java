package com.zd.esearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2021/12/26 0026 14:32
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
            HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("user",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
