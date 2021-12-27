package com.zd.esearch.provider;

import com.alibaba.fastjson.JSON;
import com.zd.esearch.dto.AccessTokenDTO;
import com.zd.esearch.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: TODO
 * @date 2021/12/26 0026 15:05
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");
         OkHttpClient client = new OkHttpClient();
         RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
         Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println("string:---->"+string);
            String token = string.split("&")[0].split("=")[1];
            System.out.println("--token---"+token);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                //将access_token加入到头部进行传递
                .addHeader("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
