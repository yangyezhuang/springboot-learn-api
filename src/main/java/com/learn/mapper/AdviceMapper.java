package com.learn.mapper;

import com.learn.model.Advice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface AdviceMapper {
    @Insert("insert into advice_tbl (id,title,content,name,phone,date) values(#{id},#{title},#{content},#{name},#{phone},#{time_});")
    int addAdvice(Long id, String title, String content, String name, String phone, String time_);

    @Delete("delete from advice_tbl where id=#{id};")
    int delAdvice(Long id);

    @Select("select * from advice_tbl;")
    List<Advice> queryAll();
}
