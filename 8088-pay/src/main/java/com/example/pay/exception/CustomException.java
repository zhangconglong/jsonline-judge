package com.example.pay.exception;

/**
 * @version: v1.0
 * @description：异常拦截返回类
 * @author: zhangconglong on 2021/11/26 16:12
 */
public class CustomException extends RuntimeException {
    private String code;
    private String msg;

    public CustomException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}