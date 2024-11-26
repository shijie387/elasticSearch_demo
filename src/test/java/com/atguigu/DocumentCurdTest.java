package com.atguigu;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.atguigu.elasticSearch_demo.model.Goods;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: atguigu
 * @create: 2024-10-23 09:26
 */
public class DocumentCurdTest {

    ElasticsearchClient client = null;

    /**
     *
     */
    @Before
    public void init() {
        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials("elastic", "111111")
        );
        RestClient restClient = RestClient
                .builder(HttpHost.create("http://192.168.200.6:9200"))
                .setHttpClientConfigCallback(hc -> hc
                        .setDefaultCredentialsProvider(credsProv)
                )
                .build();

        // Create the transport with a Jackson mapper
        RestClientTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        // And create the API client
        client = new ElasticsearchClient(transport);
    }

    /**
     * 测试文档的增删改查
     */
    @Test
    public void testDocument() throws IOException {
        Goods goods = new Goods("2", "小米手机", "小米手机.png", "小米", 4000);
        //传统方式
        //1. 创建创建文档请求构建器对象
        //IndexRequest.Builder<Goods> builder = new IndexRequest.Builder<>();
        //1.1 设置操作索引库名称
        //builder.index("my_index");
        //1.2 设置文档ID
        //builder.id("1");
        //1.3 设置文档对象
        //builder.document(goods);
        //2. 执行新增文档操作
        //IndexResponse indexResponse = client.index(builder.build());
        //3.获取ES响应结果
        //System.out.println(indexResponse);


        //Lambda表达式方式
        //IndexResponse response = client.index(a -> a.index("my_index").id("2").document(goods));
        //System.out.println(response);


        //删除文档
        //传统写法
        //DeleteRequest.Builder builder = new DeleteRequest.Builder();
        //builder.index("my_index");
        //builder.id("1");
        //DeleteResponse deleteResponse = client.delete(builder.build());
        //System.out.println(deleteResponse);

        DeleteResponse deleteResponse = client.delete(d -> d.index("my_index").id("2"));
        System.out.println(deleteResponse);

    }


}
