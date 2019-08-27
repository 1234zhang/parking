package com.netplus.catpark.service.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 16:55.
 */

public class HttpClientUtil {
    private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    public static JSONObject sendGet(String url, String param) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            //第三方服务器通过接口与微信服务器交互
            HttpGet httpGet = new HttpGet(url);
            jsonObject = null;
            //接收微信服务器返回的消息
            response = httpClient.execute(httpGet);
            //获取微信返回的信息
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //把获取的信息转格式。这里，微信发来的信息是json格式的。
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(result);
            }
        } catch (Exception e) {

        }finally {
            if(null != response){
                response.close();
            }
            if(null != httpClient){
                httpClient.close();
            }
        }
        return jsonObject;
    }
}
