package com.zd.esearch.controller;

import com.zd.esearch.dto.AccessTokenDTO;
import com.zd.esearch.dto.GithubUser;
import com.zd.esearch.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2021/12/26 0026 15:00
 */
@Controller
public class AuthorizationController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (user!=null){
            //登录成功写cookie和session
            request.getSession().setAttribute("user",user);
            System.out.println("---user---"+user.getName());
            return "redirect:/";
        }else {
            //登录失败,重新登录
            return "redirect:/";
        }
        //System.out.println("---user---"+user.getName());
        //return "index";
    }
}
