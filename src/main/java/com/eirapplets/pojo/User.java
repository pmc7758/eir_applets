package com.eirapplets.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author pangjian
 * @ClassName User
 * @Description 用户实体类
 * @date 2021/3/14 11:27
 */
@Data
@Accessors(chain = true)
public class User {

    private int id;
    private String name;
    private String grade;
    private String password;
    private String username;
    private String phone;


}
