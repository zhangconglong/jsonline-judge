package com.example.controller.ES;

import com.example.common.config.Result;
import com.example.dao.service.ES.IssueSearchESService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;
import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/issue/searchES")
public class IssueSearchController {
    
    @Resource
    IssueSearchESService issueSearchESService = new IssueSearchESService();

    /**
     * 根据时间查询
     * @param pageNum
     * @param pageSize
     * @param time
     * @return
     */
    @RequestMapping("/searchByTime")
    public Result<?> selectAllArticle(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "14") Integer pageSize,
                                      @RequestParam(defaultValue = "7") Integer time) throws IOException {
        return issueSearchESService.searchByTime(pageNum, pageSize, time);
    }

    /**
     * 分页与模糊查询
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    @RequestMapping("/fuzzySearch")
    public Result<?> fuzzySearch(@RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "14") Integer pageSize,
                                 @RequestParam(defaultValue = "") String search){
        try {
            if (search.equals("")) return issueSearchESService.searchAll(pageNum, pageSize);
            else return issueSearchESService.fuzzySearch(pageNum,pageSize,search);
        } catch (IOException e) {
            Logger.info("【error】模糊查询失败");
            return Result.error(-1, "模糊查询失败");
        }
    }

    /**
     * 根据id查询
     * @param id 公告的id
     * @return
     */
    @RequestMapping("/findById")
    public Result<?> findById(@RequestParam(defaultValue = "") String id){
        try {
            return issueSearchESService.findById(id);
        } catch (IOException e) {
            Logger.info("【error】根据id查询查询失败");
            return Result.error(-1, "根据id查询查询失败");
        }
    }
    
}
