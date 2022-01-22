package com.example.common.pojo;

import lombok.Data;

/**
 * @version: v1.0
 * @description：短信实体类
 * @author: zhangconglong on 2021/11/21 21:11
 */
@Data
public class Sms {
    /*
    电话号码
     */
    String phoneNumber;
    /*
    验证码
     */
    String code;
    /*

     */
    int min;
}