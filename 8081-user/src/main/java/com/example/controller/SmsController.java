package com.example.controller;

import com.example.common.config.Result;
import com.example.common.pojo.Sms;
import com.example.util.login.SmsUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: v1.0
 * @description： 短信
 * @author: zhangconglong on 2021/11/24 15:08
 */
@RestController
@RequestMapping(value = "/sms")
public class SmsController {

    /**
     * 发送短信验证码
     * @param sms
     * @return
     */
    @PostMapping("/sendSmsCode")
    public Result sendSmsCode(@RequestBody Sms sms){
        return new SmsUtil().sendSmsCode(sms);
    }
}