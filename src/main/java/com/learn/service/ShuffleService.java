package com.learn.service;

import com.learn.mapper.ShuffleMapper;
import com.learn.model.Shuffle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@CacheConfig(cacheNames = "course")
public class ShuffleService {

    @Autowired
    private ShuffleMapper shuffleMapper;

    //@Cacheable()
    public List<Shuffle> queryAll() {
        return shuffleMapper.queryAll();
    }
}
