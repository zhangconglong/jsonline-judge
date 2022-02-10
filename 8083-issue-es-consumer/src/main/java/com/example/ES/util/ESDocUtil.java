package com.example.ES.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.common.dto.IssueES;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.io.IOException;

/**
 * 操作ES文档
 * author: luyi
 * time: 2022/1/22 18:56
 */
@Service
public class ESDocUtil {

    @Autowired
    private RestHighLevelClient client;
    Snowflake snowflake = IdUtil.getSnowflake(1, 1);

    /**
     * 添加文档
     * @return
     * @throws IOException
     */
    public void insertDoc(String index, IssueES issue) throws IOException {

        String string = JSON.toJSONString(issue);
        System.out.println(string);

        //创建请求
        IndexRequest indexRequest = new IndexRequest(index);

        //填充内容
        indexRequest.id(String.valueOf("issue-"+snowflake.nextId()));
        indexRequest.source(string, XContentType.JSON);//将对象序列化为JSON

        //插入
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);//TODO:
        Logger.info("文章插入成功");
    }

    /**
     * 批量添加文档
     * @return
     * @throws IOException
     */
//    public boolean insertBulkDoc(String index, Map<String,Object> IssueMap) throws IOException {
//
//        IndexRequest request = new IndexRequest(index)
//                                .id(issue.getIssueId())
//                                .source();
//
//        //4，通过client对象连接ES，并执行索引创建
//        CreateIndexRequest request;
//        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
//        return response.isAcknowledged();
//    }

    /**
     * 检查是否存在索引
     * @param index
     * @return
     * @throws IOException
     */
    public boolean existDoc(String index, IssueES issue) throws IOException {
        //1，准备request对象
        GetRequest request = new GetRequest(index, issue.getIssueId());

        //2，通过client去操作
        return client.exists(request, RequestOptions.DEFAULT);
    }

    /**
     * 查询某一篇文章
     * @param index
     * @param issueId
     * @return
     * @throws IOException
     */
    public IssueES getDocById(String index, String issueId) throws IOException {
        GetRequest getRequest = new GetRequest(index, issueId);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        IssueES result = (IssueES) JSONObject.parse(getResponse.getSourceAsString());
        Logger.info(getResponse.getSourceAsString());
        Logger.info(result);
        return result;
    }

    /**
     * 查询某一篇文章
     * @param index
     * @param title
     *-=
     * @return
     * @throws IOException
     */
    public IssueES getDocByTitle(String index, String title) throws IOException {
        //构造搜索条件
        GetRequest getRequest = new GetRequest(index);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        IssueES result = (IssueES) JSONObject.parse(getResponse.getSourceAsString());
        Logger.info(getResponse.getSourceAsString());
        Logger.info(result);
        return result;
    }

}
