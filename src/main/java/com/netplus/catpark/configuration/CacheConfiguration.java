package com.netplus.catpark.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netplus.catpark.resolver.CustomObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.time.Duration;


/**
 * @program: cat-park
 * @description: 缓存的配置类
 * @author: brandon
 * @created: 2019/10/21 22:33
 */

@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {

    @Bean
    @Override
    public KeyGenerator keyGenerator(){
        return (target, method, params) ->{
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method);
            for (Object param : params) {
                sb.append(param.toString());
            }
            return sb.toString();
        };
    }

    @Bean
    /**
     * @Description: 自定义redisTemplate
     * @author: Brandon
     * @Date: 2019/10/21 22:57
     */
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        ObjectMapper objectMapper = new CustomObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // 将redis序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(factory);
        stringRedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        stringRedisTemplate.afterPropertiesSet();
        return stringRedisTemplate;
    }

    @Bean
    /**
     * @Description:自定义cacheManager
     * @author: Brandon
     * @Date: 2019/10/21 22:56
     */
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        //全局redis缓存过期时间
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofDays(1));
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
        return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
    }
}
