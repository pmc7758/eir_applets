package com.eirapplets.service;

import com.eirapplets.pojo.User;

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

}
