package com.example.jwt.util.secret;

public class SaltUtil {
    /**
     * 用于对重要信息加密
     */
    public static String getSalt(){
        String salt = "abcdefg";
        return salt;
    }

}