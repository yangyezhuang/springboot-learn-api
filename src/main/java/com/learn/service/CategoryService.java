package com.learn.service;

import com.learn.mapper.CategoryMapper;
import com.learn.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Yang Yezhuang
 * @date: 2022/3/13
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    public List<Category> listCategories() {
        return categoryMapper.queryAll();
    }


    public List<Category> listLabels() {
        return categoryMapper.queryLabel();
    }
}
