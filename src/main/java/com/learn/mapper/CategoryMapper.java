package com.learn.mapper;

import com.learn.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CategoryMapper {

    @Select("select * from category_tbl;")
    List<Category> queryAll();

    @Select("select label from category_tbl;")
    List<Category> queryLabel();
}
