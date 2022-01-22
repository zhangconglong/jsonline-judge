package com.example.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.dao.impl.EmailServiceImpl;
import com.example.common.config.Result;
import com.example.dao.mapper.UserMapper;
import com.example.common.pojo.User;
import com.example.dao.vo.UserVo;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @version: v1.0
 * @description： 用户信息修改
 * @author: zhangconglong on 2021/11/24 15:05
 */
@RestController
@RequestMapping("/user/userInfo")
public class UserInfoController {

    @Resource
    UserMapper userMapper;
    @Resource
    EmailServiceImpl emailService;

    /**
     * 邮箱注册
     * @param userVo
     * @return
     */
    @PostMapping("/emailRegister")
    public Result<?> emailRegister(@RequestBody UserVo userVo){
        System.out.println("emailRegister");
       return emailService.emailRegister(userVo);
    }

    /**
     * 修改个人信息、账号密码
     * @param email
     * @param password
     * @return
     */
//    @RequiresRoles(value = {"user","admin"}, logical = Logical.OR)
//    @RequiresAuthentication //必须是认证之后的
    @PostMapping("/modifyInfo")
    public Result<?> modifyInfo(@RequestParam String email,
                                @RequestParam String password){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getEmail, email)
                .eq(User::getPassword, password));
        if (res == null){
            return Result.error(-1, "不存在该用户");
        }else {
            res.setEmail("该用户已注销");
            res.setPassword("该用户已注销");
            userMapper.updateById(res);
            return Result.success();
        }
    }

    /**
     * 用户注销
     *
     */
//    @RequiresAuthentication //必须是认证之后的
    @PostMapping("/accountCancel") //注销
    public Result<?> accountCancel(@RequestParam String email,
                                   @RequestParam String password){
        User res = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getEmail, email)
                .eq(User::getPassword, password));
        if (res == null){
            return Result.error(-1, "不存在该用户");
        }else {
            res.setEmail("该用户已注销");
            res.setPassword("该用户已注销");
            /**
             * 将用户的可登录状态设为1
             */
            userMapper.updateById(res);
            return Result.success();
        }
    }

    /**
     * 拦截异常，转向错误信息页面
     * @return
     */
//    @ExceptionHandler(value={AuthorizationException.class})
    public Result<?> permissionError(){
        return Result.error(-1, "没有权限");
    }

    /**
     * 头像上传接口
     * @param tid
     * @param session
     * @return
     */
    @PostMapping("/addFabulous")
    public Result<?> addFabulous(@RequestBody int tid,
                                 @RequestParam HttpSession session){

        //从token中取出用户信息
        // return emailService.emailRegister(tid, session);
        return Result.success();
    }

}
