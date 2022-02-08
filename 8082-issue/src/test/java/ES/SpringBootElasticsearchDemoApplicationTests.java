package ES;

import com.example.ES.util.ESIndexUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * SpringBootElasticsearchDemoApplicationTests
 * author: luyi
 * time: 2022/2/8 16:25
 */
@SpringBootTest
public class SpringBootElasticsearchDemoApplicationTests {

//    @Autowired
//    private RestHighLevelClient client;
    @Resource
    ESIndexUtil esIndexUtil;

    @Test
    public void contextLoads() throws IOException {
//        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
//                new HttpHost(
//                        "localhost",
//                        9200,
//                        "http"
//                )
//        ));

//        boolean luyi = esIndexUtil.existIndex("luyi");
//        System.out.println(luyi);
//        System.out.println(client);
        System.out.println(esIndexUtil);
    }

}
