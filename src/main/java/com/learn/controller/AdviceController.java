package com.learn.controller;

import com.learn.model.Advice;
import com.learn.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@RestController
@RequestMapping("/advices")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    /**
     * 获取全部建议
     *
     * @return
     */
    @GetMapping()
    public List<Advice> selectAdvice() {
        return adviceService.listAdvices();
    }


    /**
     * 添加建议
     *
     * @param advice
     * @return
     */
    @PostMapping()
    public int insertAdvice(@RequestBody Advice advice) {
        return adviceService.insertAdvice(advice);
    }

    /**
     * 删除建议
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public int deleteAdvice(@PathVariable("id") Long id) {
        return adviceService.deleteAdvice(id);
    }

}
