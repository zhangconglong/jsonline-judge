package com.example.controller;

import com.example.common.config.Result;
import com.example.dao.service.IssueSearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@RequestMapping("/issue")
public class IssueSearchController {

    @Resource
    IssueSearchService issuesSearchService = new IssueSearchService();

    @RequestMapping("/searchByTime") //根据时间查询
    public Result<?> selectAllArticle(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "14") Integer pageSize,
                                      @RequestParam(defaultValue = "7") Integer time){
        return issuesSearchService.searchByTime(pageNum, pageSize, time);
    }

    @RequestMapping("/searchByCategory") //根据类别查询
    public Result<?> searchByCategory(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "14") Integer pageSize,
                                      @RequestParam(defaultValue = "vue") String category){
        return issuesSearchService.searchByCategory(pageNum, pageSize, category);
    }

    @RequestMapping("/searchByLabel") //根据Label查询
    public Result<?> searchByLabel(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "14") Integer pageSize,
                                   @RequestParam(defaultValue = "HTML") String label){
        return issuesSearchService.searchByLabel(pageNum, pageSize, label);
    }

    @RequestMapping("/fuzzySearch") //分页与模糊查询
    public Result<?> fuzzySearch(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "14") Integer pageSize,
                                 @RequestParam(defaultValue = "") String search){

        return issuesSearchService.fuzzySearch(pageNum,pageSize,search);
    }
}
