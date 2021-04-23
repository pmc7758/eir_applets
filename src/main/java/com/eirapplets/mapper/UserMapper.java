package com.eirapplets.mapper;

import com.eirapplets.pojo.PO.Temperature;
import com.eirapplets.pojo.PO.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author pangjian
 * @Interface UserMapper
 * @Description 用户数据库访问层接口
 * @date 2021/3/14 12:16
 */
@Mapper
public interface UserMapper {

    User queryUser(User user);

    void saveUser(User user);

    User queryUserByUsername(String username);

    void saveUserTemperature(Temperature temperature);

    List<String> queryPhoneNumbers(String date);

    void update(User user);

    User queryUserById(Integer id);

}
