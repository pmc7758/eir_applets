package com.eirapplets.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

/**
 * @author pangjian
 * @ClassName JwtUtil
 * @Description 令牌类
 * @date 2021/3/14 12:30
 */
@Slf4j
public class JwtUtil {
    /**
     * JWT验证过期时间 EXPIRE_TIME 分钟
     */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    /**
     * 校验token是否正确
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info("登录验证成功!");
            return true;
        } catch (Exception exception) {
            log.error("JwtUtil登录验证失败!");
            return false;
        }
    }


    /**
     * @Description: 获得token中的信息无需secret解密也能获得
     * @Param token: 生成的令牌
     * @return token中包含的用户名
     * @date 2021/3/14 12:37
    */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * @Description: 生成token签名,EXPIRE_TIME 分钟后过期
     * @Param map: 令牌map
     * @Param secret: 用户密码
     * @return java.lang.String
     * @date 2021/3/14 12:37
    */
    public static String sign(Map<String, String> map, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTCreator.Builder builder = JWT.create(); //生成Builder对象
        //生成令牌的payload
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        //指定过期时间
        String token = builder.withExpiresAt(date)
                .sign(algorithm);
        return token;
    }


}
