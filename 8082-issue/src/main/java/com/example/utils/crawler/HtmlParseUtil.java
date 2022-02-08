//package com.example.utils;
//
//import cn.hutool.json.JSONObject;
//import com.example.common.pojo.Issue;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//
////@Service
//public class HtmlParseUtil {
//    /**
//     *  将JSON文件解析成java对象，并存到MYSQL中
//     */
//    public List<Issue> htmlParse(String url) {
//        try {
//            URLConnection conn = new URL(url).openConnection() ;
//            InputStream is = conn.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//            String text = br.readLine();
//            br.close();
//
//            text = String.valueOf((new JSONObject(text)).get("result"));
//            ObjectMapper objectMapper = new ObjectMapper();
//            //允许忽略JSON中的未知字段
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            List<Issue> issueList = objectMapper.readValue(text, new TypeReference<List<Issue>>() {});
//            for (Issue issue : issueList) {
//                System.out.println("=========");
//                System.out.println(issue.getTitle());
//                System.out.println("=========");
//            }
//            return issueList;
//        } catch (IOException e) {
//            System.out.println("解析JSON失败");
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
