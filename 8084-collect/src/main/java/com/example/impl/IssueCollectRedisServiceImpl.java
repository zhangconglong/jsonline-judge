package com.example.impl;

import com.example.dao.service.IssueCollectRedisService;
import com.example.util.RedisHashMapUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class IssueCollectRedisServiceImpl implements IssueCollectRedisService {

    @Resource
    private RedisHashMapUtil redisHashMapUtil;

    /**
     * 用户点赞，并保存到redis HashMap中
     * @param issueId
     * @param userId
     */
    @Override
    public void addIssueFavour(Integer userId, Integer issueId) {
        System.out.println("正在处理请求");
        redisHashMapUtil.hset(issueId.toString(), userId.toString(), "1");
    }

    /**
     * 用户取消点赞，从HashMap中删除
     * @param issueId
     * @param userId
     */
    @Override
    public void minusIssueFavour(Integer issueId, Integer userId) {
        System.out.println("正在删除中");
        if (redisHashMapUtil.hHaashKey(issueId.toString(), userId.toString())){
            redisHashMapUtil.hdel(issueId.toString(), userId.toString());
        }

    }
}