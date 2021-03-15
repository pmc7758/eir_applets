package com.eirapplets.controller;

import com.eirapplets.pojo.User;
import com.eirapplets.service.UserService;
import com.eirapplets.utils.JwtUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pangjian
 * @ClassName UserController
 * @Description 用户控制类
 * @date 2021/3/14 11:26
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 用户登录控制器
     * @Param user: 前端传来的User对象
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @date 2021/3/14 12:45
    */
    @PostMapping("user/login")
    public Map<String,Object> login(@RequestBody User user){

        Map<String,Object> map = new HashMap<>();
        try {
            User userDB = userService.login(user);
            Map<String,String> payload = new HashMap<>();
            // 用户名作为权限判断
            payload.put("username",userDB.getUsername());
            // 生成JWT令牌
            String token = JwtUtil.sign(payload,userDB.getPassword());
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token",token);
        }catch (Exception e){
            e.printStackTrace();
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }

    /**
     * @Description: 用户注册控制器
     * @Param user: 前端传来的注册对象
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @date 2021/3/14 12:47
    */
    @PostMapping("user/register")
    public Map<String, Object> register(@RequestBody User user) {

        Map<String, Object> map = new HashMap<>();
        try {
            userService.register(user);
            map.put("state", true);
            map.put("msg", "注册成功！");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "提示:" + e.getMessage());
        }
        return map;
    }

    /**
     * @Description:测试
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @date 2021/3/15 22:01
    */
    @RequiresRoles("老师")
    @GetMapping("user/login/add")
    public Map<String,Object> add(){
        Map<String, Object> map = new HashMap<>();
        try {
            System.out.println("你是谁");
            map.put("msg", "修改成功");
            map.put("state", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", "提示:" + e.getMessage());
        }
        return map;

    }


}
