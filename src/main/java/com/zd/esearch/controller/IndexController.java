package com.zd.esearch.controller;

import com.zd.esearch.mapper.UserMapper;
import com.zd.esearch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())){
                String token = cookie.getValue();
                User user=userMapper.findByToken(token);
                if (user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        return "index";
    }

    @GetMapping("/exit")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
//        Cookie cookie = new Cookie("user",null);
//        response.addCookie(cookie);
//        cookie.setMaxAge(0);
        return "index";
    }
}
