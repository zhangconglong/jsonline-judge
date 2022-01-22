//package com.example.controller;
//
//import com.example.common.config.Result;
//import org.apache.shiro.authz.annotation.RequiresAuthentication;
//import org.apache.shiro.authz.annotation.RequiresRoles;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// * @version: v1.0
// * @description： 和管理员相关的接口
// * @author: zhangconglong on 2021/11/24 15:04
// */
//@Controller
//@RequestMapping("/admin")
//@RequiresRoles("admin")
//public class AdminController {
//
//    /**
//     * 修改题目
//     * @param email
//     * @param password
//     * @return
//     */
////    @RequiresRoles(value = {"user","admin"}, logical = Logical.OR)
//    @RequiresAuthentication //必须是认证之后的
//    @PostMapping("/modifyInfo")
//    public Result<?> modifyIssueInfo(@RequestParam String email,
//                                @RequestParam String password){
////        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
////                .eq(User::getEmail, email)
////                .eq(User::getPassword, password));
////        if (res == null){
////            return Result.error("-1", "不存在该用户");
////        }else {
////            res.setEmail("该用户已注销");
////            res.setPassword("该用户已注销");
////            userMapper.updateById(res);
//            return Result.success();
//    }
//}
