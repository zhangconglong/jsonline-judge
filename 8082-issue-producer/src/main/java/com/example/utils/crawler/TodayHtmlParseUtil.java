package com.example.utils.crawler;


import com.alibaba.fastjson.JSON;
import com.example.common.pojo.Issue;
import org.tinylog.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * 创建每日的定时任务
 * author: luyi
 * time: 2022/2/6 20:02
 */
public class TodayHtmlParseUtil {

    public static String todayURL = "http://api.h-camel.com/api?mod=interview&ctr=issues&act=today";

    /**
     *  FastJSON：将JSON文件解析成java对象，
     *  1，存到MYSQL
     *  2，加入RabbitMQ队列
     */
    public Issue todayHtmlParseUtil() {
        try {
            //1，解析链接，获取字符串
            URLConnection conn = new URL(todayURL).openConnection() ;
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String text = br.readLine();
            br.close();

            //将String解析出ISSUE
            String text01 = JSON.parseObject(text).getJSONObject("result").getJSONArray("today").getString(0);
            Logger.info("text01: "+text01);

            Issue issue1 = JSON.parseObject(text01, Issue.class);
            Logger.info("issue1: "+issue1);

            return issue1;
        } catch (IOException e) {
            Logger.error("解析JSON失败:" + e.getMessage());
            return null;
        }
    }
}
