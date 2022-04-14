package com.learn.mapper;

import com.learn.model.Advice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description: TODO
 * @Date: 2022/4/8 16:13
 * @Author: Yang Yezhuang
 */
@Mapper
public interface AdviceMapper {
    @Insert("insert into advice_tbl (id,title,content,name,phone,date) values(#{id},#{title},#{content},#{name},#{phone},#{time});")
    int addAdvice(Long id, String title, String content, String name, String phone, String time);

    @Delete("delete from advice_tbl where id=#{id};")
    int delAdvice(Long id);

    @Select("select * from advice_tbl;")
    List<Advice> queryAll();
}
