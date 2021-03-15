package com.eirapplets.mapper;

import com.eirapplets.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author pangjian
 * @Interface UserMapper
 * @Description 用户数据库访问层接口
 * @date 2021/3/14 12:16
 */
@Mapper
public interface UserMapper {

    User login(User user);

    void register(User user);

    User queryUserByUsername(String username);

}
