package com.example.ES.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.example.ES.service.ESIssueSearchService;
import com.example.ES.util.ESDocUtil;
import com.example.common.dto.IssueES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/issue")
public class ESSearchController {
    Snowflake snowflake = IdUtil.getSnowflake(1, 1);

//    @Resource
//    IssueSearchService issuesSearchService = new IssueSearchService();
//
//    @RequestMapping("/searchByTime") //根据时间查询
//    public Result<?> selectAllArticle(@RequestParam(defaultValue = "1") Integer pageNum,
//                                      @RequestParam(defaultValue = "14") Integer pageSize,
//                                      @RequestParam(defaultValue = "7") Integer time){
//        return issuesSearchService.searchByTime(pageNum, pageSize, time);
//    }
//
//    @RequestMapping("/searchByCategory") //根据类别查询
//    public Result<?> searchByCategory(@RequestParam(defaultValue = "1") Integer pageNum,
//                                      @RequestParam(defaultValue = "14") Integer pageSize,
//                                      @RequestParam(defaultValue = "vue") String category){
//        return issuesSearchService.searchByCategory(pageNum, pageSize, category);
//    }
//
//    @RequestMapping("/searchByLabel") //根据Label查询
//    public Result<?> searchByLabel(@RequestParam(defaultValue = "1") Integer pageNum,
//                                   @RequestParam(defaultValue = "14") Integer pageSize,
//                                   @RequestParam(defaultValue = "HTML") String label){
//        return issuesSearchService.searchByLabel(pageNum, pageSize, label);
//    }
//
//    @RequestMapping("/fuzzySearch") //分页与模糊查询
//    public Result<?> fuzzySearch(@RequestParam(defaultValue = "1") Integer pageNum,
//                                 @RequestParam(defaultValue = "14") Integer pageSize,
//                                 @RequestParam(defaultValue = "") String search){
//
//        return issuesSearchService.fuzzySearch(pageNum,pageSize,search);
//    }
    @Autowired
    ESIssueSearchService esissueSearchService;
    @Autowired
    ESDocUtil esDocUtil;

    @GetMapping("/insertDoc")
    public void insertDoc() throws IOException {
        IssueES issue = new IssueES();

        issue.setIssueId("issue-"+String.valueOf(snowflake.nextId()));
        issue.setAuthor("大龙");
        issue.setLabel("Vue");
        issue.setBody("详细内容");
        issue.setTitle("哈哈哈哈哈哈哈哈哈");
        issue.setReleaseTime(LocalDate.now());
        esDocUtil.insertDoc("issue",issue);
    }
}
