package com.eirapplets.filter;

import com.eirapplets.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author pangjian
 * @ClassName JwtFilter
 * @Description jwt过滤器来作为shiro的过滤器
 * @date 2021/3/15 18:18
 */
@Slf4j
@Component
public class JwtFilter extends BasicHttpAuthenticationFilter implements Filter {

    /**
     * @Description: 对登录的用户认证token
     * @Param request:
     * @Param response:
     * @return boolean
     * @date 2021/3/15 18:22
    */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");
        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        try {
            //下面的方法会直接到自定义realm中，执行认证和授权
            getSubject(request, response).login(jwtToken);
            // 如果没有抛出异常则代表登入成功，返回true
            log.info("登入成功过executeLogin");
            return true;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * @Description: 在登录的情况下会走此方法，此方法返回true直接访问控制器，这里客户端的请求会走上面的方法，返回true则代表已登录可以直接访问控制器
     * @Param request:
     * @Param response:
     * @Param mappedValue:
     * @return boolean
     * @date 2021/3/15 18:21
    */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
        } catch (Exception e) {
            log.error("JwtFilter过滤验证失败!");
            return false;
        }
    }

}
