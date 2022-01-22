package com.example.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.common.config.Result;
import com.example.common.pojo.Issue;
import com.github.pagehelper.Page;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Service
public class IssueMapperImpl {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    private Issue issue;
    private List<Issue> issueList;
    private RestHighLevelClient client;

    /**
     * 高亮查询-模糊搜索
     * @param pageNum
     * @param pageSize
     * @param search
     * @return
     */
    public Result<?> fuzzySearch(Integer pageNum, Integer pageSize, String search) throws IOException {

        //条件搜索
        SearchRequest searchRequest = new SearchRequest("");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //设置分页
        sourceBuilder.from(pageNum);
        sourceBuilder.size(pageSize);

        //根据精准匹配
        TermQueryBuilder termQueryBuilder= QueryBuilders.termQuery("title", search);
        sourceBuilder.query(termQueryBuilder);
        sourceBuilder.timeout(new TimeValue(20, TimeUnit.SECONDS));

        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //解析结果
        for (SearchHit hit : response.getHits().getHits()) {

           issueList.add(hit.getSourceAsMap());
        }
        return Result.success(issueList);
    }

    /**
     * 根据时间查询
     * @param pageNum
     * @param pageSize
     * @param time
     * @return
     */
    public Result<?> searchByTime(Integer pageNum,Integer pageSize, int time){ //显示最近X天的数据，返回json字符串

        QueryWrapper<Issue> wrapper = new QueryWrapper<>();

        Date date; Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        date=cal.getTime();String end = sdf.format(date);
        cal.add(Calendar.HOUR,-time*24);
        date=cal.getTime();String begin = sdf.format(date);

        //查询最近x天的
        if (StringUtils.isNotBlank(begin)&&StringUtils.isNotBlank(end)){
            wrapper.between("issue_pubtime", begin, end);
        }

        //查询
        wrapper.orderByDesc("issue_pubtime");
        issueList = issueMapper.selectList(wrapper);
//        for (Issue issue : issueList) {
//            System.out.println(issue.getIssueTitle());
//        }
        Page<Issue> issuePage = issueMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(issuePage);
    }

  
}
