package com.learn.controller;

import com.learn.model.Shuffle;
import com.learn.service.ShuffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 获取轮播图
 * @Date: 2022/3/13 17:31
 * @Created: by yyz
 */
@RestController
public class ShuffleController {

    @Autowired
    private ShuffleService shuffleService;

    @GetMapping("/shuffle")
    public List<Shuffle> queryAll() {
        return shuffleService.queryAll();
    }
}
