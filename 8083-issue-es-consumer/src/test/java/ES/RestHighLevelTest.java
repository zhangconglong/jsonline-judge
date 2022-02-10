//package ES;
//
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.IOException;
//
///**
// * ESTest
// * author: luyi
// * time: 2022/2/8 15:00
// */
//public class RestHighLevelTest extends SpringBootElasticsearchDemoApplicationTests{
//
//    private final RestHighLevelClient restHighLevelClient;
//
//    @Autowired
//    public RestHighLevelTest(RestHighLevelClient restHighLevelClient){
//        this.restHighLevelClient = restHighLevelClient;
//    }
//
//    /**
//     * 创建索引，创建映射
//     * @throws IOException
//     */
////    @Test
////    public void testIndexAndMapping() throws IOException {
////
////        //创建索引 插入文章
////        IndexRequest indexRequest = new IndexRequest("ems", "emp", "12");
////        indexRequest.source("{\"id\":\"433276\", \"age\":\"22\"}", XContentType.JSON);
////
////        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
////        System.out.println(indexResponse.status());
////    }
//
//    @Test
//    public void testSearch() throws IOException {
//
//        //
//        SearchRequest searchRequest = new SearchRequest("kuangshen");
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//        System.out.println(searchResponse.status());
//
////        DeleteRequest deleteRequest = new DeleteRequest();
//    }
//}
