package com.netplus.catpark.intercptor;

import com.netplus.catpark.domain.bo.ContextUser;
import com.netplus.catpark.domain.bo.RedisUser;
import com.netplus.catpark.domain.model.Response;
import com.netplus.catpark.service.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
