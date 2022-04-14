package com.learn.mapper;

import com.learn.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: 查询课程类别
 * @Date: 2022/3/14 11:04
 * @Author: yyz
 */
@Mapper
public interface CategoryMapper {

    @Select("select * from category_tbl;")
    List<Category> queryAll();

    @Select("select label from category_tbl;")
    List<Category> queryLabel();
}
