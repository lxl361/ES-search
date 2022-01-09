package com.zd.esearch.controller;

import com.zd.esearch.dto.AccessTokenDTO;
import com.zd.esearch.dto.GithubUser;
import com.zd.esearch.mapper.UserMapper;
import com.zd.esearch.model.User;
import com.zd.esearch.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: 授权
 * @date 2021/12/26 0026 15:00
 */
@Controller
public class AuthorizationController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println("-----githubuser-----"+githubUser.toString());
        if (githubUser!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            //user.setAvatarUrl(user.getAvatarUrl());
           // System.out.println("---avatarUrl---"+user.getAvatarUrl());

            userMapper.insert(user);
            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);
            //登录成功写cookie和session
            //request.getSession().setAttribute("user",githubUser);
            //System.out.println("---user---"+user.getName());
            return "redirect:/";
        }else {
            //登录失败,重新登录
            return "redirect:/";
        }
        //System.out.println("---user---"+user.getName());
        //return "index";
    }


    @GetMapping("/exit")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");//移除session
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
