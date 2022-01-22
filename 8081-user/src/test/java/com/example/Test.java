package com.example;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.UUID;

/**
 * @version: v1.0
 * @description：测试JWT
 * @author: zhangconglong on 2021/11/27 18:45
 */
public class Test {

    private final long time = 1000*60*60*24;
    //签名信息
    private final String signature = "admin";

    @org.junit.Test
    public void jwt(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken  = jwtBuilder
                //设置Header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload载荷
                .claim("username", "tom")
                .claim("role", "admin")
                //设置主题
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time)) //加入当前时间
                .setId(UUID.randomUUID().toString()) //加入UUID
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact()
                ;
        System.out.println(jwtToken);

    }
}