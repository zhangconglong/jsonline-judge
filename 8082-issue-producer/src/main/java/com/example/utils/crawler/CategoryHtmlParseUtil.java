package com.example.utils.crawler;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.common.pojo.Issue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 解析出题目类型：共13种
 * author: luyi
 * time: 2022/2/6 20:01
 */
public class CategoryHtmlParseUtil {

    public static String category = "http://api.h-camel.com/api?mod=interview&ctr=issues&act=history";

    /**
     *  将JSON文件解析成java对象，并存到MYSQL中
     */
    public List<Issue> categoryHtmlParseUtil() {
        try{
            URLConnection conn = new URL(category).openConnection() ;
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String text = br.readLine();
            br.close();

            text = String.valueOf((new JSONObject(text)).get("result"));
            ObjectMapper objectMapper = new ObjectMapper();


            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);//允许忽略JSON中的未知字段
            List<Issue> issueList = objectMapper.readValue(text, new TypeReference<List<Issue>>() {});

            for (Issue issue : issueList) {
                System.out.println(issue.getTitle());
            }
            return issueList;
        } catch (IOException e) {
            System.out.println("解析JSON失败");
            e.printStackTrace();
            return null;
        }
    }
}
