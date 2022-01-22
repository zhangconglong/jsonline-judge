package com.example.securtiy.encrypt.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.BadPaddingException;

/**
 * @version: v1.0
 * @description： base64未加密异常拦截
 * @author: zhangconglong on 2021/11/23 22:11
 */
@ResponseBody
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = {BadPaddingException.class})
    @ResponseBody
    public void zuulHandler(BadPaddingException e) {
        System.out.println("未使用网关加密，是密文传输");
    }
}