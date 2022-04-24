package com.learn.controller;

import com.learn.model.Category;
import com.learn.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<Category> listCategory() {
        return categoryService.listCategories();
    }

    @GetMapping("/catelabel")
    public List<Category> queryLabel() {
        return categoryService.listLabels();
    }
}
