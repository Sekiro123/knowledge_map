package com.jia.neo4j.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

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