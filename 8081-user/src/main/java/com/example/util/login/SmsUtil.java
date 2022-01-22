package com.example.util.login;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.example.common.config.Result;
import com.example.common.pojo.Sms;

/**
 * @version: v1.0
 * @description：发动短信实体类
 * @author: zhangconglong on 2021/11/21 21:12
 */
public class SmsUtil {

    public Result sendSmsCode(Sms sms){
        int appid = 1000000003;
        String appkey = "";
        //短信模板ID
        int templateId = 123456;
        //验证码
        String smsSign = "CNXFS";

        try{
            String[] params = {sms.getCode(), Integer.toString(sms.getMin())}; //短信中的参数
            SmsSingleSender ssender = new SmsSingleSender(appid,appkey);

            SmsSingleSenderResult result = ssender.sendWithParam("86",sms.getPhoneNumber(),
                    templateId,params,smsSign,"","");
            return Result.success();
        }catch (Exception e){
            return Result.error(-1, "短信验证码发送失败");
        }
    }
}