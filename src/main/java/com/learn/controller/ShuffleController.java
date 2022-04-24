package com.learn.controller;

import com.learn.model.Shuffle;
import com.learn.service.ShuffleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@RestController
@RequestMapping("/shuffles")
public class ShuffleController {

    @Autowired
    private ShuffleService shuffleService;

    /**
     * 获取全部轮播图url
     *
     * @return
     */
    @GetMapping()
    public List<Shuffle> shuffleList() {
        return shuffleService.listShuffle();
    }
}
