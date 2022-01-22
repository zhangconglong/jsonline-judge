package com.example.controller;

import com.example.common.config.Result;
import com.example.impl.IssueCollectRedisServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/issue/collect")
public class IssueCollectController {

    @Resource
    private IssueCollectRedisServiceImpl issueFavourRedisService;

    /**
     * 点赞文章
     */
    @PostMapping("/{issueId}/{userId}/{status}")
    public Result<?> addIssueFavour(@PathVariable("issueId") Integer issueId,
                                  @PathVariable("userId") Integer userId,
                                  @PathVariable("status")int status) {
        if (status == 1){
            issueFavourRedisService.addIssueFavour(issueId, userId);
            System.out.println("已经接收请求了");
        }else {
            issueFavourRedisService.minusIssueFavour(issueId, userId);
        }

        return Result.success();
    }
}