package com.jia.neo4j.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }
    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }
    public void expire(String key, long  time){
        redisTemplate.expire(key,time,TimeUnit.SECONDS);
    }
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
    public void delete(String key){
        redisTemplate.delete(key);
    }
    public void addSet(String key,String value){
        redisTemplate.opsForSet().add(key,value);
    }
    public Set<String> findSet(String key){
        return redisTemplate.opsForSet().members(key);
    }

}