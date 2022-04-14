package com.learn.controller;

import com.learn.model.Advice;
import com.learn.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: advice
 * @Date: 2022/4/8 16:24
 * @Author: Yang Yezhuang
 */
@RestController
@RequestMapping("/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    @PostMapping("/add")
    public int addAdvice(@RequestBody Advice advice) {
        System.out.println(advice.toString());
        return adviceService.addAdvice(advice);
    }

    @DeleteMapping("/del/{id}")
    public int delAdvice(@PathVariable("id") Long id) {
        return adviceService.delAdvice(id);
    }

    @GetMapping("/all")
    public List<Advice> selectAdvice() {
        return adviceService.queryAll();
    }
}
