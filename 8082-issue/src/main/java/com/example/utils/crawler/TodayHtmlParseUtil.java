package com.example.utils.crawler;

import cn.hutool.json.JSONObject;
import com.example.common.pojo.Issue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tinylog.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 创建每日的定时任务
 * author: luyi
 * time: 2022/2/6 20:02
 */
public class TodayHtmlParseUtil {


    public static String todayURL = "http://api.h-camel.com/api?mod=interview&ctr=issues&act=today";
    /**
     *  将JSON文件解析成java对象，并存到MYSQL中
     */
    public List<Issue> todayHtmlParseUtil() {
        try {
            //1，解析链接，获取字符串
            URLConnection conn = new URL(todayURL).openConnection() ;
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String text = br.readLine();
            br.close();

            //将String解析出ISSUE
            text = String.valueOf((new JSONObject(text)).get("result"));
            ObjectMapper objectMapper = new ObjectMapper();
            //允许忽略JSON中的未知字段
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            List<Issue> issueList = objectMapper.readValue(text, new TypeReference<List<Issue>>() {});
            for (Issue issue : issueList) {
                Logger.info("【今日文章】解析文章："+issue.getTitle());
            }
            return issueList;
        } catch (IOException e) {
            Logger.error("解析JSON失败:" + e.getMessage());
            return null;
        }
    }
}
