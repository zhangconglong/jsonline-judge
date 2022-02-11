package com.example.crawler;

import com.example.utils.crawler.CategoryHtmlParseUtil;
import com.example.utils.crawler.HistoryHtmlParseUtil;
import com.example.utils.crawler.TodayHtmlParseUtil;

/**
 * com.example.crawler.Test
 * author: luyi
 * time: 2022/2/6 20:17
 */
public class Test {

    @org.junit.Test
    public void test() {
        TodayHtmlParseUtil todayHtmlParseUtil = new TodayHtmlParseUtil();
        System.out.println(todayHtmlParseUtil.todayHtmlParseUtil());
    }

    @org.junit.Test
    public void testHistory(){
        HistoryHtmlParseUtil history = new HistoryHtmlParseUtil();
        System.out.println(history.historyHtmlParseUtil());
    }

    @org.junit.Test
    public void testCategory(){
        CategoryHtmlParseUtil category = new CategoryHtmlParseUtil();
        System.out.println(category.categoryHtmlParseUtil());
    }
}

/**
 *  将JSON文件解析成java对象，并存到MYSQL、mangoDB中
 */

/*
public class HistoryHtmlParseUtil {

    public static String historyURL = "http://api.h-camel.com/api?mod=interview&ctr=issues&act=history";


    public List<Issue> historyHtmlParseUtil() {
        try {
            URLConnection conn = new URL(historyURL).openConnection() ;
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String text = br.readLine();
            br.close();

            text = JSON.parseObject(text).getString("result");
            System.out.println(text);

            //将对象解析为List
            JSONArray array = JSONArray.parseArray(text);
            System.out.println(array);
            List<Issue> issueList = JSONObject.parseArray(text, Issue.class);


            //TODO:解析
//            ObjectMapper objectMapper = new ObjectMapper();
//            //允许忽略JSON中的未知字段
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            List<Issue> issueList = objectMapper.readValue(text, new TypeReference<List<Issue>>() {});



            for (Issue issue : issueList) {
                System.out.println(issue.getTitle());
            }
            return issueList;
        } catch (IOException e) {
            Logger.error("解析JSON失败"+e.getMessage());
            return null;
        }
    }
}


 */
