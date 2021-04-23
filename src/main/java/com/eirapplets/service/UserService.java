package com.eirapplets.service;

import com.eirapplets.pojo.PO.Temperature;
import com.eirapplets.pojo.PO.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pangjian
 * @Interface UserService
 * @Description 用户业务类接口
 * @date 2021/3/14 12:10
 */

public interface UserService {

    User login(User user);

    void register(User user);

    User findByUserName(String username);

    void userAddTemperature(Temperature temperature, HttpServletRequest httpServletRequest);

    void updateUser(User user) throws Exception;

}
