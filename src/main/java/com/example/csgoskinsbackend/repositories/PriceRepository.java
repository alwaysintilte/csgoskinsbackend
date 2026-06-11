package com.example.csgoskinsbackend.repositories;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PriceRepository {
    private final StringRedisTemplate redisTemplate;

    public PriceRepository(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    public List<Object> getPricesByKeys(List<Object> keys) {
        if (keys.isEmpty()) {
            return new ArrayList<>();
        }
        return redisTemplate.opsForHash().multiGet("item_prices", keys);
    }
    public Object getPriceByKey(String key) {
        return redisTemplate.opsForHash().get("item_prices", key);
    }
}
