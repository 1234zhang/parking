package com.netplus.catpark.intercptor;

import com.netplus.catpark.annotation.RepeatClickUrl;
import com.netplus.catpark.domain.bo.ContextUser;
import com.netplus.catpark.domain.bo.RedisUser;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Brandon.
 * @date 2019/8/17.
 * @time 12:34.
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        response.setContentType("application/json;charset=utf-8");
        if(token == null){
            response.getWriter().write(ResponseUtil.notLogin().toString());
            return false;
        }

        RedisUser redisUser = (RedisUser) redisTemplate.opsForValue().get(token);
        if(redisUser == null){
            response.getWriter().write(ResponseUtil.notLogin().toString());
            return false;
        }else{
            String url = request.getRequestURI();
            if(!reSubmit(token, url, handler, response)){
                return false;
            }
            if (!(url.contains("getAuthCode") || url.contains("checkAuthCode") || url.contains("inspect"))) {
                if(null == redisUser.getId()){
                    response.getWriter().write(new Response(1, "fail", "没有绑定手机号码").toString());
                    return false;
                }
            }
            redisTemplate.expire(token, 7L, TimeUnit.DAYS);
            ContextUser.addUser(redisUser);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContextUser.clear();
    }
    /**
     * @Description: 防止用户重复点击url
     * @author: Brandon
     * @Date: 2019/10/21 20:16
     */
    private boolean reSubmit(String token, String url, Object handle, HttpServletResponse response) throws Exception{
        if(handle instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handle;
            Method method = handlerMethod.getMethod();
            RepeatClickUrl repeatClickUrl = method.getAnnotation(RepeatClickUrl.class);
            if(repeatClickUrl != null) {
                if (isReSubmit(token, url)) {
                    response.getWriter().write(ResponseUtil.makeFail("点击太快").toString());
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * @Description: 判断redis中是否有链接与token的存储
     * @author: Brandon
     * @Date: 2019/10/21 20:22
     */
    private boolean isReSubmit(String token, String url){
        String key = token + url;
        Object value = redisTemplate.opsForValue().get(key);
        if(value == null) {
            redisTemplate.opsForValue().set(key, url, 2, TimeUnit.SECONDS);
            return false;
        }else{
            return true;
        }
    }
}
