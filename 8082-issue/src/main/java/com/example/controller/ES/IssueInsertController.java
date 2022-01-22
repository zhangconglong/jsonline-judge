package com.example.controller.ES;

import com.example.common.config.Result;
import com.example.dao.service.ES.IssueInsertESService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/issue/insertES")
public class IssueInsertController {

    @Resource
    private IssueInsertESService issueInsertESService = new IssueInsertESService();

    /**
     * 每日更新
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateArticle")
    public Result<?> updateArticle() throws Exception {

        return issueInsertESService.updateIssue();
    }

    /**
     * 初始化数据库
     * @return
     */
    @RequestMapping("/insertFirst")
    public Result<?> insertFirst() {
        try {

            return issueInsertESService.insertFirst();
        } catch (IOException e) {
            Logger.info("【error】初始数据库失败");
            Logger.info(String.valueOf(e.getCause()));
        }
    return Result.success();
    }
}
