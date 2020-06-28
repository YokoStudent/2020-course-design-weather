package com.houdada.weatherweb.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {


    /**
     * 注入String类型的redis模板
     * @param factory
     * @return
     */
     @Bean
     @ConditionalOnMissingBean(StringRedisTemplate.class)
     public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
         StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
         stringRedisTemplate.setConnectionFactory(factory);
         return stringRedisTemplate;
     }
}
