package com.learn.mapper;

import com.learn.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("select * from menu_tbl;")
    List<Menu> menu();

}
