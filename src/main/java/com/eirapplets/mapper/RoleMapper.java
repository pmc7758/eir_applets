package com.eirapplets.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author pangjian
 * @Interface RoleMapper
 * @Description 用户角色数据库访问层
 * @date 2021/3/15 18:46
 */
@Mapper
public interface RoleMapper {

    Set<String> queryRoleNamesByUsername(String username);

}
