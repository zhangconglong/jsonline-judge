package com.example.controller;

import com.example.common.config.Result;
import com.example.common.pojo.Answer;
import com.example.common.pojo.User;
import com.example.dao.service.AnswerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//要求用户是已经登录的
@RequestMapping("/answer")
public class AnswerController {

    /**
     * 查看自己的答题记录
     * @param user
     * @return
     */
    @RequestMapping("/findAllAnswer")
    public Result<?> findAllAnswer(@RequestBody User user){
        return new AnswerService().findAllAnswer(user);
    }

    /**
     * 用户进行评论
     * @param answer
     * @return
     */
    @RequestMapping("/addAnswer")
    public Result<?> addAnswer(@RequestBody Answer answer){
        return new AnswerService().addAnswer(answer);
    }

    /**
     * 用户修改评论
     * @param answer
     * @return
     */
    @RequestMapping("/modifyAnswer")
    public Result<?> modifyAnswer(@RequestBody Answer answer){
        return new AnswerService().modifyAnswer(answer);
    }

    /**
     * 用户删除评论
     * @param answer
     * @return
     */
    @RequestMapping("/deleteAnswer")
    public Result<?> deleteAnswer(@RequestBody Answer answer){
        return new AnswerService().deleteAnswer(answer);
    }

    /**
     * 用户删除评论
     * @param answer
     * @return
     */
    @RequestMapping("/addFabulous")
    public Result<?> addFabulous(@RequestBody Answer answer){
        return new AnswerService().addFabulous(answer);
    }

}