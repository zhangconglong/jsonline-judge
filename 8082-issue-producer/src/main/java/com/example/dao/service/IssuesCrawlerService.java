package com.example.dao.service;

import com.example.common.config.Result;
import com.example.common.pojo.Issue;
import com.example.dao.mapper.IssueMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IssuesCrawlerService {
    @Resource
    IssueMapper issueMapper;
    List<Issue> issueList;

//    @Resource


    private final String url = "http://api.h-camel.com/api?mod=interview&ctr=issues&act=";

    /**
     * 每日插入新题目
     * @return
     */
    @Scheduled(cron = "* * */6 * * ?") //cron表达式：每天的早上6点执行一次
    public Result<?> insertNewIssue() {
        return Result.success();
    }

    /**
     * 将爬下来的题目保存到数据库
     * @return
     */
    @Scheduled(cron = "0 0 3 L * ?") //cron表达式：每个月最后一天的3点更新一次数据库执行一次
    public Result<?> insertAllIssue(){
//        issueList = htmlParse.htmlParse(url + "categoryList");
//        if (issueList !=null){
//            for (Issue issue : issueList) {
//                issueMapper.insert(issue); //从新到旧挨个插入
//            }
//            System.out.println("插入成功");
            return Result.success();
//        }else {
//            return Result.error(-1, "saveIssue接口执行失败");
//        }
    }
}
