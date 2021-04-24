package com.eirapplets.controller;

import com.eirapplets.pojo.PO.User;
import com.eirapplets.service.SendSmsService;
import com.eirapplets.service.UserService;
import com.eirapplets.utils.JwtUtil;
import com.eirapplets.utils.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pangjian
 * @ClassName UserController
 * @Description 用户控制类
 * @date 2021/3/14 11:26
 */
@Api(tags = "用户控制器")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    private SendSmsService sendSmsService;

    /**
     * @Description: 用户登录控制器
     * @Param user: 前端传来的User对象
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @date 2021/3/14 12:45
    */
    @ApiOperation(value = "登录方法",notes = "处理post类型登录请求方法")
    @PostMapping("user/login")
    public Map<String,Object> login(
            @ApiParam(name = "登录对象",value = "用户登录填写信息后封装成用户类对象",required = true) @RequestBody User user){

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
            map.put("userid",userDB.getId());
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
    @ApiOperation(value = "注册方法", notes = "处理post类型注册请求方法")
    @PostMapping("user/register")
    public Map<String, Object> register(
            @ApiParam(name = "注册对象",value = "用户注册时填写的用户属性信息封装的用户实体类",required = true) @RequestBody User user,
            @ApiParam(name = "二维码",value = "用户注册时填写的二维码",required = true) String code,
            HttpServletRequest httpServletRequest) {

        log.info("进入注册");
        Map<String, Object> map = new HashMap<>();
        String key = (String) httpServletRequest.getServletContext().getAttribute("code");
        try {

            if(key.equalsIgnoreCase(code)){
                userService.register(user);
                map.put("state", true);
                map.put("msg", "注册成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    /**
     * @Description: 获取二维码
     * @Param httpServletRequest:
     * @return java.lang.String
     * @date 2021/4/22 10:45
    */
    @ApiOperation(value = "二维码图片获取方法",notes = "前端通过此接口可以获取4位二维码图片")
    @GetMapping("user/image")
    public String getImage(HttpServletRequest httpServletRequest){
        String code = VerifyCodeUtils.generateVerifyCode(4);
        log.info(code);
        httpServletRequest.getServletContext().setAttribute("code",code);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            VerifyCodeUtils.outputImage(120,30,byteArrayOutputStream,code);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());

    }

    /**
     * @Description: 用户修改个人信息
     * @Param user:
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @date 2021/4/22 11:01
    */
    @ApiOperation(value = "用户修改信息")
    @PostMapping("user/login/update")
    public Map<String,Object> updateUser(
            @ApiParam(name = "更新信息用户对象",value = "更新信息封装成对象，当前用户id不能为null") @RequestBody User user){

        Map<String,Object> map = new HashMap<>();

        try{

            userService.updateUser(user);
            map.put("msg","更新成功");
            map.put("state",true);

        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","更新失败");
            map.put("state",false);
        }

        return map;

    }

    /**
     * @Description:如果当天18点没有上报则自动提醒该用户上报
     * @return void
     * @date 2021/4/23 12:39
    */
    @ApiIgnore
    @Scheduled(cron = "0 0 18 * * ?")
    public void remind(){

        //sendSmsService.remind();

    }




}
