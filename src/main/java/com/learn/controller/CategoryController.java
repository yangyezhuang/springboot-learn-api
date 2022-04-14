package com.learn.controller;

import com.learn.model.Category;
import com.learn.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 查询课程类别
 * @Date: 2022/3/14 11:07
 * @Author: yyz
 */

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> queryAll() {
        return categoryService.queryAll();
    }

    @GetMapping("/catelabel")
    public List<Category> queryLabel() {
        return categoryService.queryLabel();
    }
}
