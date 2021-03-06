package com.example.dao.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.config.Result;
import com.example.dao.mapper.IssueMapper;
import com.example.common.pojo.Issue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class IssueSearchService {

    @Resource
    IssueMapper issueMapper;
    List<Issue> issueList;

//    @Resource


    /**
     * 根据标题title模糊查询
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    public Result<?> fuzzySearch(Integer pageNum, Integer pageSize, String search){
        LambdaQueryWrapper<Issue> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(search)){
            wrapper.like(Issue::getTitle, search);
        }
        Page<Issue> articlePage = issueMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(articlePage);
    }

    /**
     * 根据类别category模糊查询
     * @param pageNum
     * @param pageSize
     * @param category
     * @return
     */
    public Result<?> searchByCategory(Integer pageNum, Integer pageSize, String category){
        LambdaQueryWrapper<Issue> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.isNotBlank(category)){
            wrapper.like(Issue::getCategory, category);
        }
        Page<Issue> articlePage = issueMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(articlePage);
    }

    public Result<?> searchByLabel(Integer pageNum, Integer pageSize, String label){//根据类别标签查询

        LambdaQueryWrapper<Issue> wrapper = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(label)){
            wrapper.like(Issue::getLabel, label);
        }
        Page<Issue> articlePage = issueMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(articlePage);
    }

    /**
     * 根据时间publishTime查询
     * @param pageNum
     * @param pageSize
     * @param time
     * @return
     */
    public Result<?> searchByTime(Integer pageNum,Integer pageSize, int time){

        Date date; Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        cal.add(Calendar.HOUR,-time*24);
        date=cal.getTime();
        String begin = sdf.format(date);

        LambdaQueryWrapper<Issue> wrapper = Wrappers.lambdaQuery();

        if (StringUtils.isNotBlank(begin)){ //查询最近x天的
            wrapper.apply("UNIX_TIMESTAMP(release_time) >= UNIX_TIMESTAMP('" + begin + "')");
        }
        //查询
        //      wrapper.orderByDesc("release_time");
        issueList = issueMapper.selectList(wrapper);

        Page<Issue> articlePage = issueMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);

        return Result.success(articlePage);
    }
}
