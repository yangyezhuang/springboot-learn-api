package com.learn.service;

import com.learn.mapper.CategoryMapper;
import com.learn.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 查询课程类别
 * @Date: 2022/3/14 11:05
 * @Author: yyz
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    public List<Category> queryAll() {
        return categoryMapper.queryAll();
    }



    public List<Category> queryLabel() {
        return categoryMapper.queryLabel();
    }
}
