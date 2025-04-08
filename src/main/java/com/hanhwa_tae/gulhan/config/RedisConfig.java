package com.hanhwa_tae.gulhan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${REDIS_HOST}")
    private String host;

    @Value("${REDIS_PORT}")
    private int port;

    @Value("${REDIS_USERNAME}")
    private String username;

    @Value("${REDIS_PASSWORD}")
    private String password;

    /* RedisConfig 세팅
     * https://cobinding.tistory.com/236#Lettuce-1*/
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
        // 아래 내용들은 생성자로 불가능하여 Setter 메서드 활용
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setUsername(username);
        redisStandaloneConfiguration.setPassword(password);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        /* StringRedisSerializer: String 값을 그대로 저장하는 Serializer
        * 객체를 Redis 에 저장해주기 위해 직접 직렬화/역직렬화 하는 로직을 구성해주어야함
        * https://github.com/binghe819/TIL/blob/master/Spring/Redis/redis%20serializer/serializer.md#2-4-stringredisserializer*/
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }


}
