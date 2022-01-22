package com.example.dao.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.common.config.Result;
import com.example.dao.mapper.AnswerMapper;
import com.example.common.pojo.Answer;
import com.example.common.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Component
public class AnswerService {

    @Resource
    private AnswerMapper answerMapper;

    /**
     * 查看自己的答题记录
     * @param user
     * @return
     */
    public Result<?> findAllAnswer(@RequestBody User user){
        List<Answer> res = answerMapper.selectList(Wrappers.<Answer>lambdaQuery()
                .eq(Answer::getUserId, user.getUserId()));
        if (res == null){
            return Result.error(-1, "不存在该用户");
        }else {
            /**
             * 获取用户的评论号
             * 然后通过外键查询，取出所有题目
             */
            return Result.success(res);
        }
    }

    /**
     * 用户进行评论
     * @param answer
     * @return
     */
    public Result<?> addAnswer(@RequestBody Answer answer){

        answerMapper.insert(answer);
        return Result.success();
    }

    /**
     * 用户进行评论
     * @param answer
     * @return
     */
    public Result<?> modifyAnswer(@RequestBody Answer answer){
        answerMapper.updateById(answer);
        return Result.success();
    }

    /**
     * 用户进行评论
     * @param answer
     * @return
     */
    public Result<?> deleteAnswer(@RequestBody Answer answer){

        //将内容置为默认
        answer.setAnswerContent("该评论已经被删除");
        answerMapper.updateById(answer);
        return Result.success();
    }

    /**
     * 用户进行评论
     * @param answer
     * @return
     */
    public Result<?> addFabulous(@RequestBody Answer answer){

        //判断是否已经点过赞了
        answer.setAnswerContent("该评论已经被删除");
        answerMapper.updateById(answer);
        return Result.success();
    }
}