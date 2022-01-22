package com.example.pay.controller.dto;

import lombok.Data;

/**
 * @version: v1.0
 * @description：支付信息实体类
 * @author: zhangconglong on 2021/11/26 20:01
 */
@Data
public class AliPay {

    private String subject;
    private String traceNo;
    private String totalAmount;

}