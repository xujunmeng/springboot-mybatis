package com.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * com.aihuishou.service.quotation.redis
 * @author Mark Li
 * @version 1.0.0
 * @since 2018/5/7
 */
@Component
public class RedisService {

    @Autowired
    public StringRedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {

        this.redisTemplate = new StringRedisTemplate(redisTemplate.getConnectionFactory());
    }

    public StringRedisTemplate getRedisTemplate(String key) {

        return redisTemplate;
    }

    public void delete(String key) {

        redisTemplate.delete(key);
    }

    public <T> T execute(RedisScript<T> script, List<String> keys, Object... args) {

        return redisTemplate.execute(script, keys, args);
    }

    public void delete(Collection<String> keys) {

        redisTemplate.delete(keys);
    }

    public boolean expire(String key, long timeout, TimeUnit unit) {

        return redisTemplate.expire(key, timeout, unit);
    }

    public void set(String key, String value) {

        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, String value, long timeout, TimeUnit unit) {

        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public String get(String key) {

        return redisTemplate.opsForValue().get(key);
    }

    public Long incrby(String key, long delta) {

        return redisTemplate.opsForValue().increment(key, delta);
    }

    public void hdel(String key, Object... hashKeys) {

        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public boolean hexists(String key, String hashKey) {

        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public String hget(String key, String hashKey) {

        return (String) redisTemplate.opsForHash().get(key, hashKey);
    }

    public Map<Object, Object> hgetAll(String key) {

        return redisTemplate.opsForHash().entries(key);
    }

    public List<Object> hVals(String key) {

        return redisTemplate.opsForHash().values(key);
    }

    public void hset(String key, String hashKey, String value) {

        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void hset(String key, String hashKey, String value, long timeout, TimeUnit unit) {

        redisTemplate.opsForHash().put(key, hashKey, value);
        expire(key, timeout, unit);
    }

    public void hincrby(String key, String hashKey, long value) {

        redisTemplate.opsForHash().increment(key, hashKey, value);
    }

    public void sadd(String key, String... values) {

        redisTemplate.opsForSet().add(key, values);
    }

    public void srem(String key, String... values) {

        redisTemplate.opsForSet().remove(key, values);
    }

    public boolean sismember(String key, String value) {

        return redisTemplate.opsForSet().isMember(key, value);
    }

    public Set<String> smembers(String key) {

        return redisTemplate.opsForSet().members(key);
    }

    public Long lpush(String key, String value) {

        return redisTemplate.opsForList().leftPush(key, value);
    }

    public List<String> lrange(String key, long start, long end) {

        return redisTemplate.opsForList().range(key, start, end);
    }

    public Long llen(String key) {

        return redisTemplate.opsForList().size(key);
    }

    public String brpop(String key) {

        return redisTemplate.opsForList().rightPop(key);
    }

    public String brpop(String key, long timeout, TimeUnit unit) {

        return redisTemplate.opsForList().rightPop(key, timeout, unit);
    }

    public Long hLen(String key) {

        return redisTemplate.opsForHash().size(key);
    }

    public Set<Object> hkeys(String key) {

        return redisTemplate.opsForHash().keys(key);
    }

    public Set<String> zRangeByScore(String key, long min, long max) {

        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public boolean zSetAdd(String key,String value,Double score){

        return redisTemplate.opsForZSet().add(key,value,score);
    }

    public Long zSetRemove(String key,Object values){

        return redisTemplate.opsForZSet().remove(key,values);
    }

    public Long zRank(String key,String value){
        return redisTemplate.opsForZSet().rank(key,value);
    }
}
