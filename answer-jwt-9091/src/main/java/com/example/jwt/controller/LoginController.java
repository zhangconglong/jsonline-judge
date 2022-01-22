package com.example.jwt.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.example.common.config.Result;
import com.example.jwt.impl.EmailServiceImpl;
import com.example.jwt.util.jwt.JwtUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @version: v1.0
 * @description：登录
 * @author: zhangconglong on 2021/11/24 15:07
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private EmailServiceImpl emailService;

    /**
     * 邮箱登录：测试登录，浏览器访问： http://localhost:8081/user/emailLogin?email=zhang&password=123456
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/emailLogin")
    public Result<?> emailLogin(@RequestParam String email,
                             @RequestParam String password) {
        return emailService.emailLogin(email,password);
    }

    /**
     * 账号密码登录
     */
//    @GetMapping("/usernameLogin")
//    public Result<?> usernameLogin(@RequestParam String username,
//                                @RequestParam String password) {
//        return emailService.emailLogin(username,password);
//    }

    /**
     * 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
     * @return
     */
    @RequestMapping("isLogin")
    public Result<?> isLogin() {
        System.out.println("登录情况"+StpUtil.isLogin());
        return Result.success();
    }

    /**
     * 退出系统
     * @return
     */
    @GetMapping("/logout")
    public Result<?> logout(){
        System.out.println("退出登录");
        StpUtil.logout();
        return Result.success();
    }

    /**
     * 从头token中取用户信息
     * @return
     */
    @GetMapping("/currentUer")
    public Result<?> currentUer(@RequestHeader("Authorization") String token){
        System.out.println("用户的token = " + token);
       //解析出用户的token
        JwtUtils.getTokenInfo(token);
        return Result.success();
    }
}
