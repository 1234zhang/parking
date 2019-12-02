package com.netplus.catpark.dao.elasticsearch;


import com.netplus.catpark.service.util.MyAssert;
import com.netplus.catpark.service.util.PropertiesUtil;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @program: cat-park
 * @description: 进行连接
 * @author: brandon
 * @created: 2019/12/02 19:37
 */


public class ExRepository {
    private static final String HOST = PropertiesUtil.getProperty("es.properties", "es.host");
    private static final String PORT = PropertiesUtil.getProperty("es.properties", "es.port");
    private static final String SCHEME = PropertiesUtil.getProperty("es.properties", "es.scheme");

    private RestHighLevelClient client = null;

    public RestHighLevelClient init(String createIndex, String indexName) throws IOException {
        if(client != null){
            client.close();
        }
        MyAssert.notNull(HOST, PORT);
        client = new RestHighLevelClient(RestClient.builder(new HttpHost(HOST, Integer.parseInt(PORT), SCHEME)));
        if(isIndexExist(indexName)){
            return client;
        }
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
        request.mapping(createIndex, XContentType.JSON);
        CreateIndexResponse res = client.indices().create(request, RequestOptions.DEFAULT);
        if (!res.isAcknowledged()) {
            throw new RuntimeException("初始化失败");
        }
        return client;
    }
    private boolean isIndexExist(String index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }
}
