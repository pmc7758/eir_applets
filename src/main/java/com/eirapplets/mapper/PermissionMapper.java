package com.eirapplets.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author pangjian
 * @Interface PermissionMapper
 * @Description 用户权限数据库访问层
 * @date 2021/3/15 18:45
 */
@Mapper
public interface PermissionMapper {

    Set<String> queryPermissionByUsername(String username);

}
