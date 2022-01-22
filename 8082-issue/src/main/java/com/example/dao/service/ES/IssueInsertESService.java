package com.example.dao.service.ES;

import com.alibaba.fastjson.JSON;
import com.example.common.config.Result;
import com.example.common.pojo.Issue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luyi.crawler.util.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.IndexService;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class IssueInsertESService {

    @Resource
    private RestHighLevelClient client = null;

    private Issue issue;
//    @Value("${index}")
    String index;
//    @Value("${index-type}")
    String type;
    ObjectMapper mapper = new ObjectMapper();


    /**
     * 每天定时更新
     * @return
     * @throws Exception
     */
    @Scheduled(cron = "0 0 4 * * ?") //cron表达式：每天的凌晨4点执行一次
    public Result<?> updateIssue() throws Exception {

        GetRequest getRequest = new GetRequest(index);
        IndexRequest request = new IndexRequest(index);

        for (int i=1; i<3; i++){ //获取前两页li标签中的链接
            for (int j=1; j<15; j++) {
                issue = new HtmlParseUtil().parseHtml(listURL + i + ".htm", j);

                //不获取返回的_source上下文
                getRequest.fetchSourceContext(new FetchSourceContext(false));
                //设置排序的字段
                getRequest.storedFields("issuePubtime");
                //判断该片公告是否存在
                if (!client.exists(getRequest, RequestOptions.DEFAULT)){
                    request.source(JSON.toJSONString(issue), XContentType.JSON);
                }
                System.out.println("是否存在：" +client.exists(getRequest, RequestOptions.DEFAULT));
                //把文档插入到索引中
            }

        }
        //客户端发送请求，获取响应的结果
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println("indexResponse"+indexResponse);
        System.out.println("更新完成~~");
        return Result.success();
    }

    /**
     * 初始化数据库
     * @return
     */
//    @Scheduled(cron = "0 0 3 L * ?") //cron表达式：每个月最后一天的3点更新一次数据库执行一次
    public Result<?> insertFirst() throws IOException {

        //issueMapper.deleteAllIssue(); //请空表中的所有数据，并让主字段从1开始自增
        IndexService indexService = new IndexService() ;
        /*判断索引是否已经存在，如果已经存在，则删除，并重新建立索引；若不存在，则建立索引*/

        if(!indexService.existIndex(index)){
            indexService.deleteIndex(index);
        }
        //创建批量插入请求
        BulkRequest bulkRequest = new BulkRequest();
        for (int i=1; i<20; i++){
            for (int j = 1; j < 15; j++) {
                try {
                    issue = new HtmlParseUtil().parseHtml(listURL + i + ".htm", j);
                    if (!issue.getIssueContent().equals("")){
                        bulkRequest.add(new IndexRequest(index, type, issue.getIssueId()).source(mapper.writeValueAsString(issue), XContentType.JSON));
                    }

                }catch (Exception e2){
                    logger.info("【error】：链接异常，暂时跳过");
                }
            }
        }
        //客户端发送请求，获取响应的结果
        client.bulk(bulkRequest, RequestOptions.DEFAULT);
        logger.info("【error】：批量插入");

        return Result.success();
    }
}
