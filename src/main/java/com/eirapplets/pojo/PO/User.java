package com.eirapplets.pojo.PO;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author pangjian
 * @ClassName User
 * @Description 用户实体类
 * @date 2021/3/14 11:27
 */
@ApiModel(value = "用户实体类",description = "登录注册传输对象类")
@Data
@Accessors(chain = true)
public class User {

    @ApiModelProperty(value = "主键，由数据库自动生成",name = "id",required = false,example = "1",hidden = true)
    private int id;

    @ApiModelProperty(value = "姓名",name = "姓名(name)",required = true,example = "庞坚")
    private String name;

    @ApiModelProperty(value = "年级",name = "年级(grade)",required = true,example = "18003003")
    private String grade;

    @ApiModelProperty(value = "登录注册时提供登录对象密码属性",name = "密码(password)",required = true,example = "7758258")
    private String password;

    @ApiModelProperty(value = "学号",name = "学号(username)",required = true,example = "1800300425")
    private String username;

    @ApiModelProperty(value = "电话号码",name = "电话号码(phone)",required = true,example = "17607750063")
    private String phone;


}
