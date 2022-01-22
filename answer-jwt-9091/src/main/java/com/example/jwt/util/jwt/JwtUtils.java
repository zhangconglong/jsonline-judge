package com.example.jwt.util.jwt;

import cn.dev33.satoken.action.SaTokenActionDefaultImpl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @version: v1.0
 * @description：JWTUtils工具类
 * @author: zhangconglong on 2021/11/26 21:43
 */
public class JwtUtils extends SaTokenActionDefaultImpl {

    private static final String SING = "zhangconglong@163。com"; //签名

    /**
     * 生成token
     * @param userId
     */
    public static String createToken(Long userId){

        //
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);

        //创建JWT builder
        JwtBuilder jwtBuilder = Jwts.builder()
                //设置Header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload载荷
                .claim("username", "tom")
                .claim("role", "admin")
                //设置主题
          //      .setSubject("admin-test")
          //      .setId(String.valueOf(userId))
                //signature
          //      .signWith(SignatureAlgorithm.HS256, SING) //设置签发算法，并设置RSA私钥
                .setClaims(claims) //body数据，要唯一，自行设置
                .setIssuedAt(new Date()) //设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24*60*60*1000));//设置有效时间为1天
        String compact = jwtBuilder.compact();
        System.out.println("生成的token为"+ compact);
        return compact;
    }

    /**
     * 验证token合法性
     */
    public static String checkToken(String token){
        try{
            Jws<Claims> jwtsClaims = Jwts.parser().setSigningKey(SING).parseClaimsJws(token);//设置私钥
            Claims claims = jwtsClaims.getBody();
            return claims.getId();//
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取token信息
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token){
        System.out.println(token+"的信息为"+ JWT.require(Algorithm.HMAC256(SING)).build().verify(token));
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}