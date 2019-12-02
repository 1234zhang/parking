package com.netplus.catpark.dao.elasticsearch.mapper;

import com.alibaba.fastjson.JSON;
import com.netplus.catpark.dao.elasticsearch.ExRepository;
import com.netplus.catpark.domain.entry.EsEntry;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @program: cat-park
 * @description: 在es中查找订单详情
 * @author: brandon
 * @created: 2019/12/02 20:30
 */

@Component
public class EsOrderMapper {
    private static final String INDEX_NAME = "order";
    private static final String CREATE_INDEX = "{\n" +
            "    \"properties\": {\n" +
            "        \"id\": {\n" +
            "            \"type\": \"keyword\"\n" +
            "        },\n" +
            "        \"licensePlate\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"search_analyzer\": \"ik_max_word\"\n" +
            "        },\n" +
            "        \"address\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"search_analyzer\": \"ik_max_word\"\n" +
            "        },\n" +
            "        \"parkingTime\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"search_analyzer\": \"ik_max_word\"\n" +
            "        },\n" +
            "        \"price\": {\n" +
            "            \"type\": \"integer\"\n" +
            "        },\n" +
            "        \"orderStatus\": {\n" +
            "            \"type\": \"byte\"\n" +
            "        },\n" +
            "        \"orderId\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"search_analyzer\": \"ik_max_word\"\n" +
            "        },\n" +
            "        \"totalParkingTime\": {\n" +
            "            \"type\": \"string\",\n" +
            "            \"analyzer\": \"ik_max_word\",\n" +
            "            \"search_analyzer\": \"ik_max_word\"\n" +
            "        },\n" +
            "        \"gmtCreate\": {\n" +
            "            \"type\": \"date\"\n" +
            "        }\n" +
            "    }\n" +
            "}";

    private RestHighLevelClient client = null;
    @PostConstruct
    public void init() throws IOException {
        ExRepository repository = new ExRepository();
        client = repository.init(CREATE_INDEX, INDEX_NAME);
    }
    public void insertOrUpdateOne(EsEntry entity) {
        IndexRequest request = new IndexRequest(INDEX_NAME);
        request.id(entity.getId());
        request.source(JSON.toJSONString(entity.getData()), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 批量加载
     * @author: Brandon
     * @Date: 2019/12/2 21:46
     */
    public void insertBatch(List<EsEntry> list) {
        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(INDEX_NAME).id(item.getId())
                .source(JSON.toJSONString(item.getData()), XContentType.JSON)));
        try {
            client.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description: 批量删除
     * @author: Brandon
     * @Date: 2019/12/2 21:44
     */
    public <T> void deleteBatch(Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(INDEX_NAME, item.toString())));
        try {
            client.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 搜索
     * @author: Brandon
     * @Date: 2019/12/2 21:45
     */

    public <T> List<T> search(SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), c));
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * @Description: 单个删除
     * @author: Brandon
     * @Date: 2019/12/2 21:45
     */
    public void deleteIndex() {
        try {
            client.indices().delete(new DeleteIndexRequest(INDEX_NAME), RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @Description: 批量删除
     * @author: Brandon
     * @Date: 2019/12/2 21:46
     */
    public void deleteByQuery(QueryBuilder builder) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(INDEX_NAME);
        request.setQuery(builder);
        //设置批量操作数量,最大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            client.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
