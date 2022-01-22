package com.example.controller;

import com.example.common.config.Result;
import com.example.dao.service.IssuesCrawlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/issue")
public class IssueCrawlerController {
    @Resource
    IssuesCrawlerService issuesCrawlerService = new IssuesCrawlerService();

    //爬取题目并保存到数据库
    @GetMapping("/insertNewIssue")
    public Result<?> autoSaveIssue(){
        return issuesCrawlerService.insertNewIssue();
    }

    /**
     * 初始化数据库
     * @return
     */
    @GetMapping("/insertAllIssue")
    public Result<?> insertAllIssue(){
        try {
            return issuesCrawlerService.insertAllIssue();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(-1, "更新异常");
        }
    }
}