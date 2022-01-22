package com.example.common.config;

public enum ErrorCode {
    PARAMS_ERROR(10001, "参数错误"),
    ACCOUNT_PWD_NOT_EXIST(100002, "用户名或者密码错误"),
    NO_PERMISSION(70001, "无权访问"),
    TOKEN_ERROR(70002, "token异常"),
    TOKEN_TIME_OUT(7003, "token过期"),
    SESSION_TIME_OUT(90001, "会话超时"),
    NO_LOGIN(9002, "未登录"),;

    private int code;
    private String msg;

    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}