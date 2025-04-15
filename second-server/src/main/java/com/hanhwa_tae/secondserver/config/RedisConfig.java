package com.hanhwa_tae.secondserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Value("${spring.data.redis.password}")
    private String username;

    @Value("${spring.data.redis.username}")
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

    /* 이메일 인증할 때는 이거 쓰기! */
    /* Object 타입이 바뀌면 어떡하지 ?? */
    @Bean
    public RedisTemplate<String, Object> redisEmailTemplate() {
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        ObjectMapper objectMapper = new ObjectMapper();

//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer();

        /* Jackson2JsonRedisSerializer: JSON 값을 그대로 저장하는 Serializer
        * https://github.com/binghe819/TIL/blob/master/Spring/Redis/redis%20serializer/serializer.md#2-4-stringredisserializer*/
        redisTemplate.setKeySerializer(new Jackson2JsonRedisSerializer<>(String.class));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class)); // 여기가 답이 없네..

        return redisTemplate;
    }

    /* 로그인 저장할 때는 이거 쓰기!*/
    /* 그냥 이걸로 쓰기..*/
    @Bean
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        /* StringRedisSerializer: String 값을 그대로 저장하는 Serializer
         * 객체를 Redis 에 저장해주기 위해 직접 직렬화/역직렬화 하는 로직을 구성해주어야함
         * https://github.com/binghe819/TIL/blob/master/Spring/Redis/redis%20serializer/serializer.md#2-4-stringredisserializer*/
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        return redisTemplate;
    }

}
