package com.netplus.catpark.service.util.wxMinProgram;

import ch.qos.logback.classic.gaffer.PropertyUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netplus.catpark.domain.bo.UserInfoBO;
import com.netplus.catpark.service.util.HttpClientUtil;
import com.netplus.catpark.service.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brandon.
 * @date 2019/8/27.
 * @time 16:32.
 */

@Component
@Slf4j
public class WxMinProgramUtil  {
    private static final String APP_ID = PropertiesUtil.getProperty("wx.properties", "min.appId");
    private static final String APP_SECRET = PropertiesUtil.getProperty("wx.properties", "min.secret");

    private static final String AUTHOR_CODE = "authorization_code";

    /**
     * 获取access_token
     */
    private  static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取用于openID and 会话密钥session_Key
     */
    private static final String GET_USER_INFO = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=AUTHORCODE";
    /**
     *获取用户信息
     */
    private static final String USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";

    @Autowired
    RedisTemplate redisTemplate;

    public String getAccessTokenByRedis() throws Exception {
        String accessToken = null;
        accessToken = redisTemplate.opsForValue().get("min_access_token").toString();

        //判断过期没有
        if(accessToken == null){
            accessToken = getAccessTokenByUrl();
            redisTemplate.opsForValue().set("min_access_token", accessToken,7200);
        }
        return accessToken;
    }

    public String getAccessTokenByUrl() throws  Exception{
        String accessToken = null;
        String replace = ACCESS_TOKEN_URL.replace("APPID", APP_ID).replace("APPSECRET", APP_SECRET);
        JSONObject jsonObject = HttpClientUtil.sendGet(replace, "");
        Map<String, String> maps = null;
        if(jsonObject != null){
            maps = (Map<String,String>) JSON.parse(jsonObject.toString());
        }
        return maps.get("access_token");
    }

    public static UserInfoBO getOpenIdAndSessionKey(String code) throws IOException {
        if(code == null){
            return null;
        }
        String url = GET_USER_INFO.
                replace("APPID",APP_ID).
                replace("SECRET", APP_SECRET).
                replace("JSCODE",code).
                replace("AUTHORCODE",AUTHOR_CODE);
        JSONObject userMaps = HttpClientUtil.sendGet(url,"");
        if(userMaps.get("errcode") != null){
            log.error(userMaps.get("errcode").toString());
        }
        UserInfoBO build = UserInfoBO.builder().
                openId(userMaps.getString("openid")).
                sessionKey(userMaps.getString("session_key")).
                unionId(userMaps.getString("unionid")).build();
        return build;
    }
}
