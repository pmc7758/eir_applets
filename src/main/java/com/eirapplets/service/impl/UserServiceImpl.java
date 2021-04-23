package com.eirapplets.service.impl;

import com.eirapplets.jwt.JwtToken;
import com.eirapplets.mapper.UserMapper;
import com.eirapplets.pojo.PO.Temperature;
import com.eirapplets.pojo.PO.User;
import com.eirapplets.service.SendSmsService;
import com.eirapplets.service.UserService;
import com.eirapplets.thread.SendSmsThread;
import com.eirapplets.utils.GetDateTime;
import com.eirapplets.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
        User userDB = userMapper.queryUser(user);
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
        User userDB = userMapper.queryUser(user);
        if(userDB == null){
            userMapper.saveUser(user);
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

    /**
     * @Description: 用户添加体温信息业务
     * @Param temperature: 用户温度信息类
     * @return void
     * @date 2021/4/13 11:09
    */
    @Override
    public void userAddTemperature(Temperature temperature, HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("token");
        JwtToken jwtToken = new JwtToken(token);
        String username = (String) jwtToken.getPrincipal();

        temperature.setUserid(userMapper.queryUserByUsername(username).getId());

        temperature.setDatetime(GetDateTime.getDateTime());

        // 开起另外的线程执行报告老师
        if(Integer.parseInt(temperature.getTemperature())>37){
            SendSmsThread sendSmsThread = new SendSmsThread();
            new Thread(sendSmsThread).start();
        }

        userMapper.saveUserTemperature(temperature);

    }

    @Override
    public void updateUser(User user) throws Exception{

        User currentUserDB = userMapper.queryUserById(user.getId());

        String name = user.getName() == null ? currentUserDB.getName():user.getName();
        user.setName(name);
        String grade = user.getGrade() == null ? currentUserDB.getGrade():user.getGrade();
        user.setGrade(grade);
        if(user.getPassword()==null){
            user.setPassword(currentUserDB.getPassword());
        }else {
            user.setPassword(MD5Utils.code(user.getPassword()));
        }
        String username = user.getUsername() == null ? currentUserDB.getUsername():user.getUsername();
        user.setUsername(username);
        String phone = user.getPhone() == null ? currentUserDB.getPhone():user.getPhone();
        user.setPhone(phone);

        userMapper.update(user);

    }


}
