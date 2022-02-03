package com.zd.esearch.service;

import com.zd.esearch.mapper.UserMapper;
import com.zd.esearch.model.User;
import com.zd.esearch.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            //插入操作
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            //更新
            User dbuser = users.get(0);
//            dbuser.setGmtModified(System.currentTimeMillis());
//            dbuser.setAvatarUrl(user.getAvatarUrl());
//            dbuser.setName(user.getName());
//            dbuser.setToken(user.getToken());
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample expmale=new UserExample();
            expmale.createCriteria().andIdEqualTo(dbuser.getId());
            userMapper.updateByExampleSelective(updateUser,expmale);
        }
    }
}
