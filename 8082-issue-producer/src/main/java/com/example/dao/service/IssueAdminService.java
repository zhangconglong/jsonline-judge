package com.example.dao.service;

import com.example.common.config.Result;
import com.example.common.pojo.Issue;
import com.example.dao.mapper.IssueMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IssueAdminService {
    @Resource
    IssueMapper issueMapper;
    List<Issue> issueList;
//    LambdaQueryWrapper<Issue> wrapper = Wrappers.lambdaQuery();


    private final String url = "http://api.h-camel.com/api?mod=interview&ctr=issues&act=";

    /**
     * 修改题目内容
     * @param issue
     * @return
     */
    public Result<?> modifyIssue(Issue issue){

        try{
            issueMapper.updateById(issue);
            return Result.success();
        }catch (Exception e){
            return Result.error(-1, "题目修改失败");
        }
    }
}
