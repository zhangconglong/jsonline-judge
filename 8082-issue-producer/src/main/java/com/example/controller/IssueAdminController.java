//package com.example.controller;
//
//import com.example.common.config.Result;
//import com.example.common.pojo.Issue;
//import com.example.dao.service.IssueAdminService;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//@RequestMapping("/issue")
//public class IssueAdminController {
//
//    @Resource
//    IssueAdminService issueAdminService = new IssueAdminService();
//
//    @RequestMapping("/modifyIssue")//修改题目内容
////    @RequiresRoles(value = "admin") //要求必须有管理员权限
//    public Result<?> modifyIssue(@RequestBody Issue issue){
//
//        return issueAdminService.modifyIssue(issue);
//    }
//}
