package com.example.ES.util;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 * IndexService 操作ES索引
 * author: luyi
 * time: 2022/1/22 18:56
 */
@Service
public class ESIndexUtil {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 创建索引
     * @return
     * @throws IOException
     */
    public boolean createIndex(String index, String type) throws IOException {
        // 1，准备关于索引的settings
        Settings.Builder settings = Settings.builder()
                .put("number_of_shards", 3) //分片数
                .put("number_of_replicas", 1);//指定索引的备份数量

        //2，指定索引结构
        XContentBuilder mapping = JsonXContent.contentBuilder()
                    .startObject()
                        .startObject("properties")
                            .startObject("issue_id").field("type", "text")
                            .endObject()
                            .startObject("body").field("type", "text")
                            .endObject()
                            .startObject("author").field("type", "text")
                            .endObject()
                            .startObject("title").field("type", "text")
                            .endObject()
                            .startObject("label").field("type", "text")
                            .endObject()
                            .startObject("release_time").field("type", "date").field("format", "yyyy-MM-dd")//TODO:
                            .endObject()
                        .endObject()
                    .endObject();

        //3，将settings和mappings封装到一个Request对象中
        CreateIndexRequest request = new CreateIndexRequest(index)
                                        .settings(settings)
                                        .mapping(type, mapping);

        //4，通过client对象连接ES，并执行索引创建
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        return response.isAcknowledged();
    }

    /**
     * 检查是否存在索引
     * @param indexStr
     * @return
     * @throws IOException
     */
    public boolean existIndex(String indexStr) throws IOException {
        // 1，准备request对象
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexStr);
        //2，通过client去操作
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除一个索引
     * @param index
     * @return
     * @throws IOException
     */
    public boolean deleteIndex(String index) throws IOException {

        // 1，准备request对象
        DeleteIndexRequest request = new DeleteIndexRequest();
        request.indices(index);

        //2，通过client去操作
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }
}
