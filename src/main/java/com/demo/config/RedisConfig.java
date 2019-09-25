package com.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author james
 * @date 2019/9/25
 */
@Configuration
public class RedisConfig {

    @Autowired
    private Environment env;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(Integer.valueOf(env.getProperty("redis.maxActive")));
        poolConfig.setMaxIdle(Integer.valueOf(env.getProperty("redis.maxIdle")));
        poolConfig.setTestOnBorrow(Boolean.valueOf(env.getProperty("redis.testOnBorrow")));
        poolConfig.setTestWhileIdle(Boolean.valueOf(env.getProperty("redis.testWhileIdle")));
        poolConfig.setMaxWaitMillis(Integer.valueOf(env.getProperty("redis.maxWait")));
        return poolConfig;

    }
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(env.getProperty("redis.host"));
        factory.setPassword(env.getProperty("redis.password"));
        factory.setPort(Integer.valueOf(env.getProperty("redis.port")));
        factory.setTimeout(Integer.valueOf(env.getProperty("redis.timeout")));
        factory.setDatabase(Integer.valueOf(env.getProperty("redis.db")));
        factory.setPoolConfig(jedisPoolConfig());
        return factory;
    }

    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean(name = "redisTemplate")
    public StringRedisTemplate redisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());
        return stringRedisTemplate;
    }

    @Bean(name = "jdkSerializerRedisTemplate")
    public RedisTemplate jdkSerializerRedisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 设置值（value）的序列化采用jackson2JsonRedisSerializer。
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }

    @Bean(name = "jackson2JsonRedisTemplate")
    public StringRedisTemplate jackson2JsonRedisTemplate() {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        //将类名称序列化到json串中
        //objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
