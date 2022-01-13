package com.zd.esearch.service;

import com.zd.esearch.mapper.UserMapper;
import com.zd.esearch.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LC溪苏
 * @version 1.0
 * @description: userservice:当github登录后根据accountId来查询是否存在当前用户登录状态,若
 *              存在则更新token,否则则进行插入操作
 * @date 2022/1/13 0013 10:54
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        User dbuser=userMapper.findByAccountId(user.getAccountId());
        if (dbuser==null){
            //插入操作
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            dbuser.setGmtModified(System.currentTimeMillis());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.update(dbuser);
        }
    }
}
