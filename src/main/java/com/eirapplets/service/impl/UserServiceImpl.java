package com.eirapplets.service.impl;

import com.eirapplets.mapper.UserMapper;
import com.eirapplets.pojo.User;
import com.eirapplets.service.UserService;
import com.eirapplets.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pangjian
 * @ClassName UserServiceImpl
 * @Description 用户业务实现类
 * @date 2021/3/14 12:12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 登录业务
     * @Param user: 登录的用户
     * @return com.eirapplets.pojo.User
     * @date 2021/3/14 12:15
    */
    @Override
    public User login(User user) {
        user.setPassword(MD5Utils.code(user.getPassword()));
        User userDB = userMapper.login(user);
        if(userDB != null){
            return userDB;
        }
        throw new RuntimeException("登录失败");
    }

    /**
     * @Description: 注册业务
     * @Param user:
     * @return void
     * @date 2021/3/15 18:13
    */
    @Override
    public void register(User user) {
        user.setPassword(MD5Utils.code(user.getPassword()));
        User userDB = userMapper.login(user);
        if(userDB == null){
            userMapper.register(user);
        }else {
            throw new RuntimeException("学号重复！");
        }
    }

    /**
     * @Description: 通过用户姓名查询用户
     * @Param username:
     * @return com.eirapplets.pojo.User
     * @date 2021/3/15 18:39
    */
    @Override
    public User findByUserName(String username) {
        User userDB = userMapper.queryUserByUsername(username);
        return userDB;
    }


}
