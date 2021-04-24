package com.eirapplets.shiro;

import com.eirapplets.jwt.JwtToken;
import com.eirapplets.mapper.PermissionMapper;
import com.eirapplets.mapper.RoleMapper;
import com.eirapplets.pojo.PO.User;
import com.eirapplets.service.UserService;
import com.eirapplets.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * @author pangjian
 * @ClassName MyRealm
 * @Description 自定义一个授权
 * @date 2021/3/15 18:24
 */
@Slf4j
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserService userService;


    /**
     * @Description: 判断左边的对象是否属于右边类的实例，返回Boolean类型值,该方法判断令牌是否被支持
     * @Param token:
     * @return boolean
     * @date 2021/3/15 18:26
    */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * @Description: 从数据库查询该用户应有的角色和权限
     * @Param principalCollection:
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @date 2021/3/15 18:36
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = JwtUtil.getUsername(principalCollection.toString());

        User user = userService.findByUserName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(user != null){
            log.info("用户授权");
            Set<String> roleName = roleMapper.queryRoleNamesByUsername(username);
            Iterator iterator = roleName.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next());
            }
            Set<String> ps = permissionMapper.queryPermissionByUsername(username);
            //获取用户的用户名，因为认证成功可以从认证消息中拿出来
            //String username = (String) principalCollection.iterator().next();
            //根据用户名查询当前用户的角色列表
            //Set<String> roleName = roleMapper.queryRoleNamesByUsername(username);
            //根据用户名查询当前用户的权限列表
            //Set<String> ps = permissionMapper.queryPermissionByUsername(username);
            //封装
            //SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.setRoles(roleName);
            info.setStringPermissions(ps);

        }
        return info;
    }
    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        log.info(token);
        // 解密获得username，用于和数据库进行对比
        String username = null;
        try {
            //这里工具类没有处理空指针等异常这里处理一下(这里处理科学一些)
            username = JwtUtil.getUsername(token);
        } catch (Exception e) {
            throw new AuthenticationException("heard的token拼写错误或者值为空");
        }
        if (username == null) {
            log.error("token无效(空''或者null都不行!)");
            throw new AuthenticationException("token无效");
        }
        User userBean = userService.findByUserName(username);
        if (userBean == null) {
            log.error("用户不存在!)");
            throw new AuthenticationException("用户不存在!");
        }
        if (!JwtUtil.verify(token, username, userBean.getPassword())) {
            log.error("用户名或密码错误(token无效或者与登录者不匹配)!)");
            throw new AuthenticationException("用户名或密码错误(token无效或者与登录者不匹配)!");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
