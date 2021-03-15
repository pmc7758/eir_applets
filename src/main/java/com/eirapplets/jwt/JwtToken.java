package com.eirapplets.jwt;

import com.eirapplets.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author pangjian
 * @ClassName JwtToken
 * @Description JwtToken:实现shiro的AuthenticationToken接口的类JwtToken
 * @date 2021/3/15 18:16
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    /**
     * @Description:拿到token里的用户名
     * @return java.lang.Object
     * @date 2021/3/15 18:23
    */
    @Override
    public Object getPrincipal() {
        return JwtUtil.getUsername(token);
    }

    /**
     * @Description:返回token
     * @return java.lang.Object
     * @date 2021/3/15 22:12
    */
    @Override
    public Object getCredentials() {
        return token;
    }

}
