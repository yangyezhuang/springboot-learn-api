package com.learn.service;

import com.learn.mapper.ShuffleMapper;
import com.learn.model.Shuffle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Service
//@CacheConfig(cacheNames = "course")
public class ShuffleService {

    @Autowired
    private ShuffleMapper shuffleMapper;

    /**
     * 获取全部轮播图url
     * @return
     */
    //@Cacheable()
    public List<Shuffle> listShuffle() {
        return shuffleMapper.queryAll();
    }
}
